package io.github.joxit.pizzeria.vertx;

import io.github.joxit.pizzeria.dto.PizzaDTO;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.*;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.TimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class HttpServerVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaVerticle.class);

    private static final long TIMEOUT = 30000;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        LOGGER.info("Start Http Server Verticle");
        Router router = Router.router(vertx);

        CorsHandler corsHandler = CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader(HttpHeaders.AUTHORIZATION.toString())
                .allowedHeader(HttpHeaders.CONTENT_TYPE.toString());
        router.route().handler(corsHandler);

        router.route().handler(TimeoutHandler.create(TIMEOUT));

        BodyHandler bodyHandler = BodyHandler.create().setBodyLimit(1024 * 1024);
        router.route().handler(bodyHandler);
        router.route().handler(this::handle);

        router.route().failureHandler(ErrorHandler.create(true));

        HttpServerOptions options = new HttpServerOptions()
                .setHost("0.0.0.0")
                .setPort(8081);

        vertx.createHttpServer(options)
                .requestHandler(router::accept)
                .listen(res -> {
                    if (res.succeeded()) {
                        LOGGER.info("Vertex server started");
                        startFuture.complete();
                    } else {
                        LOGGER.info("Vertex server failed to start");
                        startFuture.fail(res.cause());
                    }
                });
    }

    private void handle(RoutingContext ctx) {
        HttpServerRequest request = ctx.request();
        HttpServerResponse response = ctx.response();

        EventBus eb = ctx.vertx().eventBus();
        eb.<List<PizzaDTO>>send(PizzaVerticle.name, request.getParam("type"), ar -> {
            if (ar.failed()) {
                LOGGER.error("Error in pizzeria", ar.cause());
                ctx.fail(ar.cause());
            } else {
                response.putHeader("Content-Type", "application/json")
                        .setStatusCode(200)
                        .end(Json.encode(ar.result().body()));
            }
        });
    }
}

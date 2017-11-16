package io.github.joxit.pizzeria.vertx;

import io.netty.util.ResourceLeakDetector;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class VertexExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(VertexExample.class);

    @Autowired
    private Vertx vertx;

    @Autowired
    private SpringVerticleFactory verticleFactory;


    @PostConstruct
    private void deployVerticle() {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);

        vertx.registerVerticleFactory(verticleFactory);

        EventBus eventBus = vertx.eventBus();

        List<Future> futures = new ArrayList<>();

        // Scale the verticles on cores
        int cores = Runtime.getRuntime().availableProcessors();
        DeploymentOptions options = new DeploymentOptions().setInstances(cores);
        Future<String> future = Future.future();
        futures.add(future);
        vertx.deployVerticle(verticleFactory.prefix() + ":" + HttpServerVerticle.class.getName(), options, future.completer());

        future = Future.future();
        futures.add(future);
        vertx.deployVerticle(verticleFactory.prefix() + ":" + PizzaVerticle.class.getName(), future.completer());

        eventBus.registerDefaultCodec(ArrayList.class, ObjectMessageCodec.of(ArrayList.class));

        CompositeFuture.all(futures)
                .setHandler(ar -> {
                    if (ar.succeeded()) {
                        LOGGER.info("Pizzeria app is started");
                    } else {
                        LOGGER.error("Error in server initialization", ar.cause());
                        System.exit(1);
                    }
                });
    }

    @Bean
    protected Vertx vertx() {
        return Vertx.vertx();
    }

    private static class ObjectMessageCodec<T> implements MessageCodec<T, T> {

        private final Class<T> clazz;

        /**
         * Constructs a new ObjectMessageCodec.
         * @param clazz the class
         * @param <T> the type of the class
         * @return a new instance
         */
        private static <T> ObjectMessageCodec<T> of(Class<T> clazz) {
            return new ObjectMessageCodec<>(clazz);
        }

        /**
         * Constructs a new ObjectMessageCodec from a class.
         * @param clazz the class
         */
        private ObjectMessageCodec(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void encodeToWire(Buffer buffer, T msg) {
            // TODO implements
            throw new IllegalStateException("Not implemented");
        }

        @Override
        public T decodeFromWire(int pos, Buffer buffer) {
            // TODO implements
            throw new IllegalStateException("Not implemented");
        }

        @Override
        public T transform(T msg) {
            return msg;
        }

        @Override
        public String name() {
            return this.getClass().getSimpleName() + "<" + clazz.getSimpleName() + ">";
        }

        @Override
        public byte systemCodecID() {
            return -1;
        }
    }
}

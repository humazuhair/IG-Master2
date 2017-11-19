FROM openjdk:8-jre-alpine

WORKDIR /usr/src/pizzeria

COPY target/pizzeria-vertx-jar-with-dependencies.jar pizzeria-vertx.jar

CMD java $JAVA_OPTS -jar pizzeria-vertx.jar
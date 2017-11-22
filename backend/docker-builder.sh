#!/bin/bash

set -e

mvn -P servlet clean package

docker build -t joxit/pizzeria:servlet pizzeria-servlet/


mvn package -P vertx

docker build -t joxit/pizzeria:vertx pizzeria-vertx/
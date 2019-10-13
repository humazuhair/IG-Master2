rootProject.name = "pizzeria"
include("pizzeria-core", "pizzeria-servlet", "pizzeria-vertx", "pizzeria-boot")

project(":pizzeria-core").projectDir = File("$rootDir/pizzeria-core")
project(":pizzeria-servlet").projectDir = File("$rootDir/pizzeria-servlet")
project(":pizzeria-vertx").projectDir = File("$rootDir/pizzeria-vertx")
project(":pizzeria-boot").projectDir = File("$rootDir/pizzeria-boot")
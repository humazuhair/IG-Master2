import org.gradle.jvm.tasks.Jar

plugins {
  java
}

tasks.jar {
  enabled = true
  archiveName = "pizzeria-vertx.jar"
  manifest.attributes["Main-Class"] = "io.github.joxit.pizzeria.vertx.VertexExample"
  // This line of code recursively collects and copies all of a project's files
  // and adds them to the JAR itself. One can extend this task, to skip certain
  // files or particular types at will
  from(configurations.runtime.get().map({ if (it.isDirectory) it else zipTree(it) }))
}

dependencies {
  implementation(project(":pizzeria-core"))
  implementation("org.springframework:spring-jdbc")
  implementation("org.springframework:spring-context")
  implementation("org.springframework:spring-orm")
  implementation("org.springframework.data:spring-data-jpa")

  implementation("io.vertx:vertx-web:${property("version.vertx")}")
  implementation("javax.annotation:javax.annotation-api:${property("version.javax")}")
  implementation("org.javassist:javassist:${property("version.javassist")}")
}

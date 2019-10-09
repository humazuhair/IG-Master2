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
  compile(project(":pizzeria-core"))
  compile("io.vertx:vertx-web:3.5.4")
  compile("javax.annotation:javax.annotation-api:1.3.2")
  compile("javax.xml.bind:jaxb-api:2.1")
  compile("org.javassist:javassist:3.24.0-GA")
}

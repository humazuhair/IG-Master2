plugins {
  java
  id("war")
}

tasks.war {
  enabled = true
  archiveFileName.set("pizzeria-servlet.war")
}

dependencies {
  compile(project(":pizzeria-core"))
  implementation("org.springframework:spring-web:${project.rootProject.ext["springVersion"]}")
  implementation("org.springframework:spring-webmvc:${project.rootProject.ext["springVersion"]}")
  implementation("org.springframework:spring-webflux:${project.rootProject.ext["springVersion"]}")
  implementation("javax:javaee-api:8.0.1")
  implementation("jstl:jstl:1.2")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
  compileOnly("javax.servlet:javax.servlet-api:4.0.1")
  compileOnly("org.apache.tomcat.embed:tomcat-embed-core:9.0.29")
}

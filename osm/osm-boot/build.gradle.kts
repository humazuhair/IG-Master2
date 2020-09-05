plugins {
  java
  id("org.springframework.boot") version "2.3.3.RELEASE"
}

tasks.bootJar {
  archiveName = "osm-boot.jar"
  manifest.attributes["Implementation-Version"] = project.version
}

dependencies {
  compile(project(":osm-core"))

  implementation("com.github.ben-manes.caffeine:caffeine:${property("version.caffeine")}")

  implementation("mil.nga.sf:sf-geojson:${property("version.sf-geojson")}")

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-configuration-processor:")
  implementation("org.springframework.boot:spring-boot-starter-cache")
}

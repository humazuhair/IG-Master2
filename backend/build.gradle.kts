buildscript {
  extra.apply {
    set("springVersion", "5.1.2.RELEASE")
    set("mysqlVersion", "8.0.13")
  }
}

plugins {
  java
  maven
  id("idea")
}

allprojects {
  apply(plugin = "maven")
  apply(plugin = "idea")

  group = "io.github.joxit"
  version = "1.0-SNAPSHOT"
}

subprojects {
  apply(plugin = "java")

  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  repositories {
    mavenLocal()
    mavenCentral()

    maven { url = uri("https://repo.maven.apache.org/maven2") }
  }
}

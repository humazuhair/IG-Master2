import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  java
  maven
  id("idea")
  id("com.github.ben-manes.versions") version "0.27.0"
  kotlin("jvm") version "1.3.60" apply false
  id("org.jetbrains.kotlin.plugin.spring") version "1.3.60" apply false
}

buildscript {
  extra.apply {
    set("springVersion", "5.2.1.RELEASE")
  }
}
fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
  val regex = "^[0-9,\\.v\\-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

tasks.dependencyUpdates {
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

allprojects {
  apply(plugin = "maven")
  apply(plugin = "idea")

  group = "io.github.joxit"
  version = "1.0-SNAPSHOT"
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "kotlin")

  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  repositories {
    mavenLocal()
    mavenCentral()

    maven { url = uri("https://repo.maven.apache.org/maven2") }
  }

  dependencies {
    implementation(kotlin("stdlib"))
  }
}

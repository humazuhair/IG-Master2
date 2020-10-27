plugins {
  java
  idea
  maven
  kotlin("jvm") version "1.4.0"
  id("com.github.ben-manes.versions") version "0.30.0"
  id("org.springframework.boot") version "2.3.3.RELEASE" apply false
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

  repositories {
    mavenLocal()
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "kotlin")

  java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.3.3.RELEASE"))
    implementation(kotlin("stdlib"))
  }
}

plugins {
  java
  idea
  kotlin("jvm")
  id("com.github.ben-manes.versions")
  id("org.springframework.boot") apply false
  id("com.github.johnrengelman.shadow") apply false
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

  tasks {
    compileKotlin {
      kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
      }
    }

  }

  java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:${property("version.spring.boot")}"))
    implementation(kotlin("stdlib"))
  }
}

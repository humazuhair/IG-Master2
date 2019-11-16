description = "Core module"
plugins {
  `java-library`
  id("kotlin")
}

val xmlgraphicsVersion = "1.12"

dependencies {
  api("com.jolbox:bonecp:0.8.0.RELEASE")
  api("org.slf4j:jcl-over-slf4j:1.7.29")
  api("ch.qos.logback:logback-classic:1.2.3")
  api("org.apache.xmlgraphics:batik-svggen:${xmlgraphicsVersion}")
  api("org.apache.xmlgraphics:batik-dom:${xmlgraphicsVersion}")
  api("org.apache.xmlgraphics:batik-transcoder:${xmlgraphicsVersion}")
  api("org.apache.xmlgraphics:xmlgraphics-commons:2.4")
  api("org.apache.xmlgraphics:batik-util:${xmlgraphicsVersion}")
  api("org.springframework:spring-context:${project.rootProject.ext["springVersion"]}")
}


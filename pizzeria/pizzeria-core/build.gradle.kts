description = "Core module"
plugins {
  `java-library`
}

dependencies {
  api("com.jolbox:bonecp:0.8.0.RELEASE")
  api("org.springframework:spring-jdbc:${project.rootProject.ext["springVersion"]}")
  api("org.springframework:spring-context:${project.rootProject.ext["springVersion"]}")
  api("org.springframework:spring-orm:${project.rootProject.ext["springVersion"]}")
  api("org.springframework.data:spring-data-jpa:2.2.2.RELEASE")
  api("xml-apis:xml-apis:2.0.2")
  api("org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.2")
  api("org.slf4j:jcl-over-slf4j:1.7.29")
  api("ch.qos.logback:logback-classic:1.2.3")
  api("mysql:mysql-connector-java:${project.rootProject.ext["mysqlVersion"]}")
  api("org.hibernate:hibernate-entitymanager:5.4.9.Final")
  api("javax.xml.bind:jaxb-api:2.3.1")
}


/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java

    kotlin("jvm") version "1.8.10"

    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "it.unicam.cs.ids"
version = "3.0.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // DATABASE POSTGRESQL
    runtimeOnly("org.postgresql:postgresql")

    // LOMBOK
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // DATABASE H2
    // runtimeOnly("com.h2database:h2")

    // JWT AUTHENTICATION DEPENDENCIES
    // implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    // runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    // runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    // OTHER DEPENDENCIES
    //implementation("org.springframework.boot:spring-boot-starter-mail")
    //implementation("org.springdoc:springdoc-openapi-ui:1.5.10")

    // TEST DEPENDENCIES
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
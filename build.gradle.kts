plugins {
    alias(libs.plugins.kotlin.jvm) apply true
}

group = "org.github.swszz"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_21
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stblib)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.jackson.databind)
    testImplementation(libs.junit.api)
    testImplementation(libs.junit.engine.jupiter)
    testImplementation(libs.assertj)
    testImplementation(libs.kotlin.junit.test)
    testImplementation(libs.kotlin.coroutines.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

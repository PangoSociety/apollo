



plugins {
    // TODO: Move ktLint and jvm to Convention
    id("apollo.global.ktlint")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.i18n4k)
}

group = "dev.pango.apollo"
version = "0.0.1-SNAPSHOT"

i18n4k {
    sourceCodeLocales = listOf("es", "en")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    // Ktor
    implementation(libs.ktor.config.yaml)
    implementation(libs.ktor.contentnegotiation)
    implementation(libs.ktor.core)
    implementation(libs.ktor.kotlinx.serialization)
    implementation(libs.ktor.netty)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.auth.jwt)
    // Dependency Injection
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    // Persistence
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.hikariCP)
    implementation(libs.exposed.jdbc)
    implementation(libs.postgresql.driver)
    // Tests
    testImplementation(libs.ktor.tests)
    testImplementation(libs.kotlin.test.junit)
    implementation(libs.kotlin.faker)
    // Functional
    implementation(libs.arrow.core)
    implementation(libs.arrow.fxcoroutines)
    // Logging
    implementation(libs.logback.classic)
    implementation(libs.jbcrypt)
    // I18n
    implementation(libs.i18n4k.jvm)
}

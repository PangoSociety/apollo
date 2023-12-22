plugins {
    // TODO: Move ktLint and jvm to Convention
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.i18n4k)
    id("apollo.global.ktlint")
}


group = "dev.pango.apollo"
version = "0.0.1-SNAPSHOT"

i18n4k {
    sourceCodeLocales = listOf("es")
}

application {
    mainClass.set("dev.pango.apollo.backend.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    // Ktor
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
    implementation(libs.ktorm.core)
    implementation(libs.ktorm.support.postgresql)
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
    // I18n
    implementation(libs.i18n4k.jvm)
}

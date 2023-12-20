package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.modules.userauth.di.userAuthModule
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    install(Koin) {
        slf4jLogger()
        modules(userAuthModule)
    }
//    install(CORS) {
//        anyHost()
//        allowHeader(HttpHeaders.ContentType)
//    }
//    install(Koin) {
//        slf4jLogger()
//
//    }
}

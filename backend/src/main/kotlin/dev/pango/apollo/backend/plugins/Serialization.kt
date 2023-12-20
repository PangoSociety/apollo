package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.modules.userauth.di.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.koin.ktor.plugin.*
import org.koin.logger.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    install(Koin) {
        slf4jLogger()
        modules(userAuthModule)
    }
}

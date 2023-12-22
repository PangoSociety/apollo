package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.modules.educational.di.*
import dev.pango.apollo.backend.modules.userauth.di.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.*
import org.koin.logger.*

fun Application.configureDependencyInjection() {
    install(Koin) {
        slf4jLogger()
        modules(userAuthModule, educationalModule)
    }
}

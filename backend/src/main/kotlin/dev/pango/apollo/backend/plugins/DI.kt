package dev.pango.apollo.backend.plugins

import dev.pango.apollo.backend.modules.educational.di.*
import dev.pango.apollo.backend.modules.sharedkernel.di.*
import dev.pango.apollo.backend.modules.userauth.di.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.*
import org.koin.ktor.plugin.*
import org.koin.logger.*

fun Application.configureDependencyInjection() {
    val coreModule =
        module {
            single<ApplicationConfig> { this@configureDependencyInjection.environment.config }
        }

    install(Koin) {
        // TODO: Change logger to logback or reverse, we cannot use multiple loggers
        slf4jLogger()
        modules(
            coreModule,
            sharedKernelModule,
            userAuthModule,
            educationalModule,
        )
    }
}

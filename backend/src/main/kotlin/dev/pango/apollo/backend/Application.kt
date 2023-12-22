package dev.pango.apollo.backend

import arrow.core.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.mapper.*
import dev.pango.apollo.backend.modules.userauth.presentation.apirest.*
import dev.pango.apollo.backend.plugins.*
import dev.pango.apollo.backend.routes.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureDependencyInjection() // This should be the first one
    configureSerialization()
    configureAuth()
    configureUserRoutes()
    configureFileRoutes()
    configureRouting()
}

suspend inline fun <reified R : Any> ApplicationCall.respondEither(either: Either<TranslatableFailure, R>) {
    either.fold(
        { failure -> respond(failure.i18nMessage.toFailureDTO()) },
        { data -> respond(data) }
    )
}

suspend inline fun <reified R : Any> ApplicationCall.respondEither(httpStatusCode: HttpStatusCode, either: Either<TranslatableFailure, R>) {
    either.fold(
        { failure -> respond(httpStatusCode, failure.i18nMessage.toFailureDTO()) },
        { data -> respond(httpStatusCode, data) }
    )
}

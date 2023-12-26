package dev.pango.apollo.backend.framework.http

import arrow.core.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.intl.*
import dev.pango.apollo.backend.modules.sharedkernel.mapper.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend inline fun <reified R : Any> ApplicationCall.respondEither(either: Either<TranslatableFailure, R>) {
    either.fold(
        { failure -> respond(failure.i18nMessage.toFailureDTO()) },
        { data -> respond(data) },
    )
}

suspend inline fun <reified R : Any> ApplicationCall.respondEither(
    httpStatusCode: HttpStatusCode,
    either: Either<TranslatableFailure, R>,
) {
    either.fold(
        { failure -> respond(httpStatusCode, failure.i18nMessage.toFailureDTO()) },
        { data -> respond(httpStatusCode, data) },
    )
}

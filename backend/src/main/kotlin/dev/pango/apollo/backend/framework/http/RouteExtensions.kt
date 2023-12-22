package dev.pango.apollo.backend.framework.http

import de.comahe.i18n4k.*
import io.ktor.http.*
import io.ktor.server.request.*

fun HeaderValue.toLocale() = Locale(this.value)

fun List<HeaderValue>.getDefaultLocale() = first().toLocale()

fun ApplicationRequest.getLocale() = this.acceptLanguageItems().getDefaultLocale()
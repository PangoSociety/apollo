package com.example.plugins

import com.example.modules.userauth.di.userAuthModule
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
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
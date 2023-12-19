package com.example

import com.example.dao.*
import com.example.modules.userauth.presentation.apirest.configureUserRoutes
import com.example.plugins.*
import com.example.routes.configureFileRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureAuth()
        configureUserRoutes()
        configureFileRoutes()
    }.start(wait = true)

}
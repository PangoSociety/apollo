package dev.pango.apollo.backend.modules.sharedkernel.infraestructure.config

import io.ktor.server.config.*

class ApolloConfig constructor(config: ApplicationConfig) {
//    val databaseUser = config.property("database.user").getString()
//    val databasePassword = config.property("database.password").getString()
//    val databaseHost = config.property("database.host").getString()
//    val databasePort = config.property("database.port").getString()
//    val databaseDriver = config.property("database.driver").getString()
//    val databaseMaxPoolSize = config.property("database.maxPoolSize").getString().toInt()
//    val env = config.property("config.env").getString().toEnv()

    val databaseUser = "postgres"
    val databasePassword = "postgres"
    val databaseHost = "localhost"
    val databasePort = "5432"
    val databaseDriver = "org.postgresql.Driver"
    val databaseMaxPoolSize = 10
    val env = "DEVELOPMENT".toEnv()

    private fun String.toEnv(): Environment {
        return Environment.valueOf(this)
    }

    fun isProduction() = env == Environment.PRODUCTION
}

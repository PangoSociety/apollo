package dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence

import com.zaxxer.hikari.*
import dev.pango.apollo.backend.modules.educational.infraestructure.persistence.entity.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.config.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.koin.ktor.ext.*
import javax.sql.*

fun Application.initDatabase() {
    val apolloConfig by inject<ApolloConfig>()
    val tables = arrayOf(CourseTable)
    val dataSource =
        createDataSource(
            driverClassName = apolloConfig.databaseDriver,
            password = apolloConfig.databasePassword,
            host = apolloConfig.databaseHost,
            port = apolloConfig.databasePort,
            dbName = "apollo_${apolloConfig.env.rawValue}",
            maxPoolSize = apolloConfig.databaseMaxPoolSize,
            user = apolloConfig.databaseUser,
        )
    Database.connect(dataSource)
    transaction {
        SchemaUtils.createMissingTablesAndColumns(*tables)
    }
}

private fun createDataSource(
    driverClassName: String,
    password: String,
    host: String,
    port: String,
    dbName: String,
    maxPoolSize: Int,
    user: String,
): DataSource {
    val config = HikariConfig()
    config.driverClassName = driverClassName
    config.password = password
    config.jdbcUrl = "jdbc:postgresql://$host:$port/postgres"
//    config.jdbcUrl = "jdbc:postgresql://$host:$port/$dbName"
    config.maximumPoolSize = maxPoolSize
    config.username = user
    config.validate()
    return HikariDataSource(config)
}

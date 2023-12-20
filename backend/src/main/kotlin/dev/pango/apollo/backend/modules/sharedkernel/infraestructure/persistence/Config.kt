package dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence

import org.ktorm.database.Database

val database =
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres",
    )

package dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface UserKtorm : Entity<UserKtorm> {
    companion object : Entity.Factory<UserKtorm>()

    val id: Int
    var firstName: String
    var lastName: String
    var email: String
    var password: String
}

object UserTable : Table<UserKtorm>("users") {
    val id = int("id").primaryKey().bindTo(UserKtorm::id)
    val firstName = varchar("firstname").bindTo(UserKtorm::firstName)
    val lastName = varchar("lastname").bindTo(UserKtorm::lastName)
    val email = varchar("email").bindTo(UserKtorm::email)
    val password = varchar("password").bindTo(UserKtorm::password)
}

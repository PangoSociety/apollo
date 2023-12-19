package com.example.modules.userauth.infraestructure.persistence.tables

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface User : Entity<User> {
    companion object : Entity.Factory<User>()
    val id: Int?
    var firstName: String
    var lastName: String
    var email: String
}

object UserTable : Table<User>("users") {
    val id = int("id").primaryKey().bindTo(User::id)
    val firstName = varchar("firstname").bindTo(User::firstName)
    val lastName = varchar("lastname").bindTo(User::lastName)
    val email = varchar("email").bindTo(User::email)
}

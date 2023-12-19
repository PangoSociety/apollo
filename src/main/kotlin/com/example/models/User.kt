package com.example.models

import kotlinx.serialization.*
import org.ktorm.entity.*
import org.ktorm.schema.*


@Serializable
data class UserRequest(val firstName: String, val lastName: String, val email: String)

@Serializable
data class UserResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
)

@Serializable
data class ErrorResponse(val message: String)

interface User : Entity<User> {
    companion object : Entity.Factory<User>()
    val id: Int?
    var firstName: String
    var lastName: String
    var email: String
}

object Users : Table<User>("users") {
    val id = int("id").primaryKey().bindTo(User::id)
    val firstName = varchar("firstname").bindTo(User::firstName)
    val lastName = varchar("lastname").bindTo(User::lastName)
    val email = varchar("email").bindTo(User::email)
}

//object Users: Table() {
//    val id = integer("id").autoIncrement()
//    val firstName = varchar("firstName", 128)
//    val lastName = varchar("lastName", 128)
//    val email = varchar("email", 128)
//
//    override val primaryKey = PrimaryKey(id)
//}
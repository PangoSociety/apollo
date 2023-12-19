package com.example.dao

import com.example.models.User
import com.example.models.UserRequest
import com.example.models.Users
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*

 val database = Database.connect(
    url = "jdbc:postgresql://localhost:5432/postgres",
    driver = "org.postgresql.Driver",
    user = "postgres",
    password = "postgres"
)
class UserService {
    fun createUser(userRequest: UserRequest): Boolean {
        val newUser = User {
            firstName = userRequest.firstName
            lastName = userRequest.lastName
            email = userRequest.email
        }
        val affectedRecordsNumber =
            database.sequenceOf(Users)
                .add(newUser)
        return affectedRecordsNumber == 1
    }

    fun getUserByCredentials(firstName: String, lastName: String, email: String): User? {
        return database.sequenceOf(Users)
            .filter { it.firstName eq firstName }
            .filter { it.lastName eq lastName }
            .filter { it.email eq email }
            .toList()
            .singleOrNull()

    }

    fun findAllUsers(): Set<User> =
        database.sequenceOf(Users).toSet()
}

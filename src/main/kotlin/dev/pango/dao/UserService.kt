package dev.pango.dao

import dev.pango.models.User
import dev.pango.models.UserRequest
import dev.pango.models.Users
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
            dev.pango.dao.database.sequenceOf(Users)
                .add(newUser)
        return affectedRecordsNumber == 1
    }

    fun getUserByCredentials(firstName: String, lastName: String, email: String): User? {
        return dev.pango.dao.database.sequenceOf(Users)
            .filter { it.firstName eq firstName }
            .filter { it.lastName eq lastName }
            .filter { it.email eq email }
            .toList()
            .singleOrNull()

    }

    fun findAllUsers(): Set<User> =
        dev.pango.dao.database.sequenceOf(Users).toSet()
}

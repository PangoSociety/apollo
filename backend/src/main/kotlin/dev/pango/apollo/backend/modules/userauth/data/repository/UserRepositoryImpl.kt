package dev.pango.apollo.backend.modules.userauth.data.repository

import arrow.core.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence.*
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables.*
import kotlinx.serialization.*
import org.ktorm.dsl.*
import org.ktorm.entity.*

class UserRepositoryImpl : UserRepository {

    override suspend fun searchUser(id: Int): User? =
        database.sequenceOf(UserTable)
            .find { user -> user.id eq id }

    override suspend fun findAllUsers(): Set<User> =
        database.sequenceOf(UserTable).toSet()

    override suspend fun updateUser(
        id: Int,
        firstname: String,
    ): Either<Failure, Unit> {
        return try {
            val foundUser = searchUser(id)
            foundUser?.firstName = firstname
            val affectedRecordsNumber = foundUser?.flushChanges()
            if (affectedRecordsNumber == 1) {
                Either.Right(Unit)
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun deleteUser(id: Int): Either<Failure, Unit> {
        return try {
            val foundUser = searchUser(id)
            val affectedRecordsNumber = foundUser?.delete()
            if (affectedRecordsNumber == 1) {
                Either.Right(Unit)
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun createUser(
        firstname: String,
        lastname: String,
        email: String,
    ): Either<Failure, Unit> {
        try {
            val newUser =
                User {
                    this.firstName = firstname
                    this.lastName = lastname
                    this.email = email
                }
            val affectedRecordsNumber =
                database.sequenceOf(UserTable)
                    .add(newUser)

            return if (affectedRecordsNumber == 1) {
                Either.Right(Unit)
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            return Either.Left(Failure.Generic(e))
        }
    }
}

@Serializable
data class ErrorResponse(val message: String)

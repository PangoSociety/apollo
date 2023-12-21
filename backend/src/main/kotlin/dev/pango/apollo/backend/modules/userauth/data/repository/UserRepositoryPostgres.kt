package dev.pango.apollo.backend.modules.userauth.data.repository

import arrow.core.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.persistence.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.tables.*
import dev.pango.apollo.backend.modules.userauth.mapper.toUserDomain
import kotlinx.serialization.*
import org.ktorm.dsl.*
import org.ktorm.entity.*

class UserRepositoryPostgres : UserRepository {
    override suspend fun findById(id: Int): Either<Failure, User> {
        return try {
            val data = getUserFromDb(id)
            if (data is UserKtorm) {
                Either.Right(data.toUserDomain())
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun findAll(): Either<Failure, List<User>> {
        return try {
            val data =
                database.sequenceOf(UserTable).toList()
            if (data.isNotEmpty()) {
                Either.Right(data.map { it.toUserDomain() })
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun updateUser(
        user: User,
    ): Either<Failure, User> {
        return try {
            val foundUser = getUserFromDb(user.id)
            if (foundUser != null) {
                foundUser.firstName = user.firstName
                foundUser.lastName = user.lastName
                foundUser.email = user.email
                val affectedRecordsNumber = foundUser.flushChanges()
                if (affectedRecordsNumber == 1) {
                    Either.Right(foundUser.toUserDomain())
                } else {
                    Either.Left(Failure.DatabaseError)
                }
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            Either.Left(Failure.Generic(e))
        }
    }

    override suspend fun deleteUser(id: Int): Either<Failure, Unit> {
        return try {
            val foundUser = getUserFromDb(id)
            if (foundUser is UserKtorm) {
                val affectedRecordsNumber = foundUser.delete()
                if (affectedRecordsNumber == 1) {
                    Either.Right(Unit)
                } else {
                    Either.Left(Failure.DatabaseError)
                }
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
    ): Either<Failure, User> {
        try {
            val newUser =
                UserKtorm {
                    this.firstName = firstname
                    this.lastName = lastname
                    this.email = email
                }
            val affectedRecordsNumber =
                database.sequenceOf(UserTable)
                    .add(newUser)

            return if (affectedRecordsNumber == 1) {
                Either.Right(newUser.toUserDomain())
            } else {
                Either.Left(Failure.DatabaseError)
            }
        } catch (e: Exception) {
            return Either.Left(Failure.Generic(e))
        }
    }

    private fun getUserFromDb(id: Int): UserKtorm? {
        val data =
            database.sequenceOf(UserTable)
                .find { user -> user.id eq id }
        return data
    }
}

@Serializable
data class ErrorResponse(val message: String)

package dev.pango.modules.userauth.data.repository

import arrow.core.Either
import dev.pango.modules.sharedkernel.domain.failure.Failure
import dev.pango.modules.sharedkernel.infraestructure.persistence.*

import dev.pango.modules.userauth.domain.repository.UserRepository
import dev.pango.modules.userauth.infraestructure.persistence.tables.*
import kotlinx.serialization.Serializable
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf

class UserRepositoryImpl : UserRepository {

//    override suspend fun createUser(userEntity: CreateUserEntity): Boolean {
//        val newUser = User {
//            firstName = userEntity.firstName
//            lastName = userEntity.lastName
//            email = userEntity.email
//        }
//        val affectedRecordsNumber =
//            database.sequenceOf(UserTable)
//                .add(newUser)
//        return affectedRecordsNumber == 1
//    }

    override suspend fun searchUser(id: Int): User? =
        database.sequenceOf(UserTable)
            .find { user -> user.id eq id }

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

    override suspend fun createUser2(
        firstname: String,
        lastname: String,
        email: String,
    ): Either<Failure, Unit> {
        try {
            val newUser = User {
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
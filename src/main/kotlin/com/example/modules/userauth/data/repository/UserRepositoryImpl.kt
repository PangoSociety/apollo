package com.example.modules.userauth.data.repository

import arrow.core.Either
import com.example.modules.sharedkernel.domain.failure.Failure
import com.example.modules.sharedkernel.infraestructure.persistence.database

import com.example.modules.userauth.domain.entity.CreateUserEntity
import com.example.modules.userauth.domain.entity.DeleteUserEntity
import com.example.modules.userauth.domain.entity.UpdateUserEntity
import com.example.modules.userauth.domain.repository.UserRepository
import com.example.modules.userauth.infraestructure.persistence.tables.*
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
        id:Int,
        userEntity: UpdateUserEntity,
    ): Either<Failure, Unit> {
        return try {
            val foundUser = searchUser(id)
            foundUser?.firstName = userEntity.firstName
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

    override suspend fun deleteUser(userEntity: DeleteUserEntity): Either<Failure, Unit> {
        return try {
            val foundUser = searchUser(userEntity.id)
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

    override suspend fun createUser2(userEntity: CreateUserEntity): Either<Failure, Unit> {
        try {
            val newUser = User {
                firstName = userEntity.firstName
                lastName = userEntity.lastName
                email = userEntity.email
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
package com.example.modules.userauth.domain.repository

import arrow.core.Either
import com.example.modules.sharedkernel.domain.failure.Failure
import com.example.modules.userauth.domain.entity.*
import com.example.modules.userauth.infraestructure.persistence.tables.User

interface UserRepository {
//    suspend fun createUser(userEntity: CreateUserEntity): Boolean
    suspend fun updateUser(id:Int, userEntity: UpdateUserEntity): Either<Failure, Unit>
    suspend fun deleteUser(userEntity: DeleteUserEntity): Either<Failure, Unit>
    suspend fun createUser2(userEntity: CreateUserEntity): Either<Failure, Unit>
    suspend fun searchUser(id: Int): User?

}
package dev.pango.apollo.backend.modules.userauth.domain.repository

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.Failure
import dev.pango.apollo.backend.modules.userauth.domain.entity.User

interface UserRepository {
    suspend fun updateUser(
        user: User
    ): Either<Failure, User>

    suspend fun deleteUser(id: Int): Either<Failure, Unit>

    suspend fun createUser(
        firstname: String,
        lastname: String,
        email: String,
    ): Either<Failure, User>

    suspend fun findById(id: Int): Either<Failure, User>

    suspend fun findAll(): Either<Failure, List<User>>
}
package dev.pango.apollo.backend.modules.userauth.domain.repository

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.Failure
import dev.pango.apollo.backend.modules.userauth.domain.entity.AuthResponse
import dev.pango.apollo.backend.modules.userauth.domain.entity.RefreshTokenResponse
import dev.pango.apollo.backend.modules.userauth.domain.entity.User

interface UserRepository {

    suspend fun updateUser(
        user: User,
    ): Either<Failure, User>

    suspend fun deleteUser(id: Int): Either<Failure, Unit>

    suspend fun createUser(
        firstname: String,
        lastname: String,
        email: String,
    ): Either<Failure, User>

    suspend fun findById(id: Int): Either<Failure, User>

    suspend fun findAll(): Either<Failure, List<User>>

    suspend fun getUserByCredentials(firstName: String, lastname: String): Either<Failure, User>

    suspend fun authUser(
        firstName: String,
        lastname: String,
    ): Either<Failure, AuthResponse>

    suspend fun refreshToken(token:String) : Either<Failure, RefreshTokenResponse>
}

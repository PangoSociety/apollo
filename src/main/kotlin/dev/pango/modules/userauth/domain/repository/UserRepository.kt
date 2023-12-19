package dev.pango.modules.userauth.domain.repository

import arrow.core.Either
import dev.pango.modules.sharedkernel.domain.failure.Failure
import dev.pango.modules.userauth.infraestructure.persistence.tables.User

interface UserRepository {
    suspend fun updateUser(id: Int, firstname: String): Either<Failure, Unit>
    suspend fun deleteUser(id: Int): Either<Failure, Unit>
    suspend fun createUser2(
        firstname: String,
        lastname: String,
        email: String,
    ): Either<Failure, Unit>

    suspend fun searchUser(id: Int): User?

}
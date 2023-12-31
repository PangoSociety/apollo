package dev.pango.apollo.backend.modules.userauth.domain.repository

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import java.util.UUID

interface UserRepository {

    fun updateUser(
        user: User,
    ): Either<RepositoryFailure, User>

    fun deleteUser(id: UUID): Either<RepositoryFailure, Unit>

    fun createUser(
        user: User,
    ): Either<RepositoryFailure, User>

    fun getUserById(id: UUID): Either<RepositoryFailure, User>

    fun getUserList(): Either<RepositoryFailure, List<User>>


    //todo change ti email
    fun getUserByEmail(email: String): Either<RepositoryFailure, User>
//    fun authUser(
//        userName: String,
//        password: String,
//    ): Either<RepositoryFailure, Tokens>
    fun refreshToken(token: String): Either<RepositoryFailure, RefreshTokenResponse>
}

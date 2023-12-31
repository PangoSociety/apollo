package dev.pango.apollo.backend.modules.userauth.domain.repository

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.RefreshToken

interface AuthRepository {

    fun findEmailByToken(token: String): Either<RepositoryFailure, String>

    fun saveRefreshToken(refreshToken: String, email: String): Either<RepositoryFailure, RefreshToken>

}
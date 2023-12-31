package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.RefreshToken
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.domain.repository.AuthRepository
import java.util.UUID

class SaveRefreshTokenUseCase (
    private val authRepository: AuthRepository
    ){

    operator fun invoke(refreshToken: String, email: String): Either<UserDomainFailure, RefreshToken> {
        return authRepository.saveRefreshToken(refreshToken, email).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.RefreshTokenNotAvailable
            }
        }
    }

}
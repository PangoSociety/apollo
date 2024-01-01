package dev.pango.apollo.backend.modules.userauth.aplication

import arrow.core.Either
import dev.pango.apollo.backend.modules.userauth.domain.entity.AuthToken
import dev.pango.apollo.backend.modules.userauth.domain.entity.Tokens
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.domain.usecase.LoginUserUseCase
import dev.pango.apollo.backend.modules.userauth.domain.usecase.SaveRefreshTokenUseCase
import dev.pango.apollo.backend.modules.userauth.domain.usecase.UpdateAccessTokenByRefreshTokenUseCase

// Un application service puede ser injectado con casos de uso y con repositorios
class AuthApplicationService(
    private val loginUserUseCase: LoginUserUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val updateAccessTokenByRefreshTokenUseCase: UpdateAccessTokenByRefreshTokenUseCase,
) {
    fun loginUser(
        email: String,
        password: String,
    ): Either<UserDomainFailure, Tokens> {
        return loginUserUseCase(email, password)
    }

    fun accessUser(refreshToken: String): Either<UserDomainFailure, AuthToken> {
        return updateAccessTokenByRefreshTokenUseCase(refreshToken)
    }

//    fun save(refreshToken: UUID, username: String): Either<UserDomainFailure, RefreshToken> {
//        return saveRefreshTokenUseCase(refreshToken, username)
//    }
}

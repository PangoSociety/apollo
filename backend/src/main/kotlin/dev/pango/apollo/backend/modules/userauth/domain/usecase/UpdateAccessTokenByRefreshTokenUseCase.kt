package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import dev.pango.apollo.backend.framework.jwt.JwtService
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.AuthToken
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.domain.repository.AuthRepository
import dev.pango.apollo.backend.modules.userauth.domain.repository.UserRepository

class UpdateAccessTokenByRefreshTokenUseCase(
    private val authRepository: AuthRepository,
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
) {

    operator fun invoke(refreshToken: String): Either<UserDomainFailure, AuthToken> =
        either {
            val decodedRefreshToken = jwtService.verifyRefreshToken(refreshToken)
            val userFoundEmail = authRepository.findEmailByToken(refreshToken).mapLeft {
                when (it) {
                    is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserFoundByTokenNotAvailable
                }
            }.bind()
            println("refreshToken : $refreshToken")
            println("decodedRefreshToken : $decodedRefreshToken")
            ensureNotNull(decodedRefreshToken) {
                UserDomainFailure.DecodedRefreshTokenNotAvailable
            }
            val foundUser = userRepository.getUserByEmail(userFoundEmail).mapLeft {
                when (it) {
                    is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserFoundByEmailNotAvailable
                }
            }.bind()
            val emailFromRefreshToken =
                decodedRefreshToken.getClaim("email").asString()
            ensure(emailFromRefreshToken == foundUser.email) {
                UserDomainFailure.EmailDecodedFoundNotAvailable
            }
            AuthToken(
                jwtService.createAccessToken(userFoundEmail)
            )
        }
}
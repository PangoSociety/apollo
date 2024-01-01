package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.framework.crypto.CryptoService
import dev.pango.apollo.backend.framework.jwt.JwtService
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.domain.failure.*
import dev.pango.apollo.backend.modules.userauth.domain.repository.*

// Los application services pueden usar casos de uso
// Pero los casos de uso no puedes usar application services
// TODO: injectas userrepository
class LoginUserUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val cryptoService: CryptoService,
) {
    operator fun invoke(
        email: String,
        password: String,
    ): Either<UserDomainFailure, Tokens> {
        val data =
            userRepository.getUserByEmail(email).map {
                if (cryptoService.checkPassword(password, it.password)) {
                    val accessToken = jwtService.createAccessToken(email)
                    val refreshToken = jwtService.createRefreshToken(email)
                    val either = authRepository.saveRefreshToken(refreshToken, email)
                    return either.map { _ -> Tokens(accessToken = accessToken, refreshToken = refreshToken) }.mapLeft {
                        when (it) {
                            is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserRefreshTokenNotAvailable
                        }
                    }
                } else {
                    Tokens(refreshToken = "", accessToken = "")
                }
            }.mapLeft {
                when (it) {
                    is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserRegisterNotAvailable
                }
            }
        return data
    }
//    if(cryptoService.checkPassword(password, it.password)) {
//        val accessToken = jwtService.createAccessToken(username)
//        val refreshToken = jwtService.createRefreshToken(username)
//        val either = authRepository.saveRefreshToken(refreshToken, username)
//        return either.map { _ -> Tokens(accessToken = accessToken, refreshToken = refreshToken) }.mapLeft {
//            when(it) {
//                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserByIdNotAvailable
//            }
//        }
//    }else {
//        Tokens(refreshToken = "", accessToken = "")
//    }
}

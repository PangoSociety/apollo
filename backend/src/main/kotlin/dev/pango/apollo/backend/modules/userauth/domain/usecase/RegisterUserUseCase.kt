package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.framework.crypto.CryptoService
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.domain.repository.UserRepository

class RegisterUserUseCase(
    private val userRepository: UserRepository,
    private val cryptoService: CryptoService,
) {
    operator fun invoke(user: User): Either<UserDomainFailure, User> {
        return userRepository.createUser(
            User(
                id = user.id,
                userName = user.userName,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                password = cryptoService.hashPassword(user.password),
            ),
        ).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserRegisterNotAvailable
            }
        }
    }
}

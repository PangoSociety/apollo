package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.domain.repository.UserRepository

class UpdateUserUseCase (
    private val userRepository: UserRepository
){
    operator fun invoke(user: User) : Either<UserDomainFailure, User> {
        return userRepository.updateUser(user).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserUpdateNotAvailable
            }
        }
    }
}
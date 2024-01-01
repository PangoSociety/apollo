package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.domain.failure.*
import dev.pango.apollo.backend.modules.userauth.domain.repository.*

class ShowUserListUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Either<UserDomainFailure, List<User>> {
        return userRepository.getUserList().mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserListNotAvailable
            }
        }
    }
}

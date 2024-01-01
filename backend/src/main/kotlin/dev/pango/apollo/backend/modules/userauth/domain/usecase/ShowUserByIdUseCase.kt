package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.domain.failure.*
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import java.util.UUID

class ShowUserByIdUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(id: UUID): Either<UserDomainFailure, User> {
        return userRepository.getUserById(id).mapLeft {
            when (it) {
//                    TODO 1
                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserByIdNotAvailable
            }
        }
    }
}

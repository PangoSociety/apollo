package dev.pango.apollo.backend.modules.userauth.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.domain.repository.UserRepository
import java.util.UUID

class DeleteUserUseCase (
    private val userRepository: UserRepository
){
    operator fun invoke(id: UUID): Either<UserDomainFailure, Unit> {
        return userRepository.deleteUser(id).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> UserDomainFailure.UserDeleteNotAvailable
            }
        }
    }
}
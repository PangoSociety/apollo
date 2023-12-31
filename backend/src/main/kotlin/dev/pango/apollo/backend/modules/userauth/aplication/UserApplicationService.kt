package dev.pango.apollo.backend.modules.userauth.aplication

import arrow.core.Either
import dev.pango.apollo.backend.modules.userauth.domain.entity.*
import dev.pango.apollo.backend.modules.userauth.domain.failure.*
import dev.pango.apollo.backend.modules.userauth.domain.usecase.*
import java.util.UUID

class UserApplicationService(
    private val showUserListUseCase: ShowUserListUseCase,
    private val showUserByIdUseCase: ShowUserByIdUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) {

    fun listAllUsers(): Either<UserDomainFailure, List<User>> {
        return showUserListUseCase()
    }

    fun listUserById(id: UUID): Either<UserDomainFailure, User> {
        return showUserByIdUseCase(id)
    }

    fun createUser(user: User): Either<UserDomainFailure, User> {
        return registerUserUseCase(user)
    }

    fun deleteUser(id: UUID): Either<UserDomainFailure, Unit> {
        return deleteUserUseCase(id)
    }

    fun updateUser(user: User) : Either<UserDomainFailure, User> {
        return updateUserUseCase(user)
    }
}


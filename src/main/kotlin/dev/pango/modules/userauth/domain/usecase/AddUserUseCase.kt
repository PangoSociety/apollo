package dev.pango.modules.userauth.domain.usecase

import dev.pango.modules.userauth.domain.entity.CreateUserEntity
import dev.pango.modules.userauth.domain.repository.UserRepository

class AddUserUseCase constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        data: CreateUserEntity
    ) = userRepository.createUser2(data)
}
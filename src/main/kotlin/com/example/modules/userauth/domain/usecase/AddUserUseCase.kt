package com.example.modules.userauth.domain.usecase

import com.example.modules.userauth.domain.entity.CreateUserEntity
import com.example.modules.userauth.domain.repository.UserRepository

class AddUserUseCase constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        data: CreateUserEntity
    ) = userRepository.createUser2(data)
}
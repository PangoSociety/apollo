package com.example.modules.userauth.di

import com.example.modules.userauth.data.repository.UserRepositoryImpl
import com.example.modules.userauth.domain.repository.UserRepository
import org.koin.dsl.module

val userAuthModule = module {
    single<UserRepository> {
        UserRepositoryImpl()
    }
}
package dev.pango.modules.userauth.di

import dev.pango.modules.userauth.data.repository.UserRepositoryImpl
import dev.pango.modules.userauth.domain.repository.UserRepository
import org.koin.dsl.module

val userAuthModule = module {
    single<UserRepository> {
        UserRepositoryImpl()
    }
}
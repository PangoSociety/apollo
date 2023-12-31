package dev.pango.apollo.backend.modules.userauth.di

import dev.pango.apollo.backend.modules.userauth.data.repository.UserRepositoryExposed
import dev.pango.apollo.backend.modules.userauth.domain.repository.UserRepository
import org.koin.dsl.module

val userAuthModule =
    module {
        single<UserRepository> {
            UserRepositoryExposed()
        }
    }

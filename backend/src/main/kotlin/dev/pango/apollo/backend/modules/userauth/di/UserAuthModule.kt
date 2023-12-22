package dev.pango.apollo.backend.modules.userauth.di

import dev.pango.apollo.backend.modules.userauth.aplication.service.AuthService
import dev.pango.apollo.backend.modules.userauth.aplication.service.AuthServiceImpl
import dev.pango.apollo.backend.modules.userauth.data.repository.RefreshTokenMap
import dev.pango.apollo.backend.modules.userauth.data.repository.UserRepositoryPostgres
import dev.pango.apollo.backend.modules.userauth.domain.repository.RefreshTokenRepository
import dev.pango.apollo.backend.modules.userauth.domain.repository.UserRepository
import org.koin.dsl.module

val userAuthModule =
    module {
        single<AuthService> {
            AuthServiceImpl()
        }
        single<RefreshTokenRepository> {
            RefreshTokenMap()
        }

        single<UserRepository> {
            UserRepositoryPostgres(
                authService = get<AuthService>(),
                refreshTokenRepository = get<RefreshTokenRepository>()
            )
        }
    }

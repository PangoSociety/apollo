package dev.pango.apollo.backend.modules.userauth.di

import dev.pango.apollo.backend.framework.crypto.CryptoService
import dev.pango.apollo.backend.framework.jwt.JwtService
import dev.pango.apollo.backend.modules.userauth.aplication.AuthApplicationService
import dev.pango.apollo.backend.modules.userauth.aplication.UserApplicationService
import dev.pango.apollo.backend.modules.userauth.domain.repository.*
import dev.pango.apollo.backend.modules.userauth.domain.usecase.*
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.AuthRepositoryExposed
import dev.pango.apollo.backend.modules.userauth.infraestructure.persistence.UserRepositoryExposed
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.facade.UserServiceFacade
import org.koin.dsl.module

val userAuthModule =
    module {
        single<UserRepository> {
            UserRepositoryExposed()
//            UserRepositoryPostgres(
//                authService = get<AuthService>(),
//                refreshTokenRepository = get<RefreshTokenRepository>()
//            )
        }

        single<AuthRepository> {
            AuthRepositoryExposed()
        }

        single<AuthApplicationService> { AuthApplicationService(get(), get(), get()) }
        single<JwtService> { JwtService() }
        single<CryptoService> { CryptoService() }
        single<UserApplicationService> { UserApplicationService(get(), get(), get(), get(), get()) }
//        single<AuthService> {
//            AuthServiceImpl()
//        }
//        single<AuthRepository> {
//            RefreshTokenMap()
//        }
        single {
            UserServiceFacade(get(), get())
        }

        single<ShowUserListUseCase> { ShowUserListUseCase(get()) }

        single<ShowUserByIdUseCase> { ShowUserByIdUseCase(get()) }

        single<RegisterUserUseCase> { RegisterUserUseCase(get(), get()) }

        single<DeleteUserUseCase> { DeleteUserUseCase(get()) }

        single<UpdateUserUseCase> { UpdateUserUseCase(get()) }

        single<LoginUserUseCase> { LoginUserUseCase(get(), get(), get(), get()) }

        single<SaveRefreshTokenUseCase> { SaveRefreshTokenUseCase(get()) }

        single<UpdateAccessTokenByRefreshTokenUseCase> { UpdateAccessTokenByRefreshTokenUseCase(get(), get(), get()) }
    }

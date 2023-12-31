package dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.facade

import arrow.core.Either
import dev.pango.apollo.backend.modules.userauth.aplication.AuthApplicationService
import dev.pango.apollo.backend.modules.userauth.aplication.UserApplicationService
import dev.pango.apollo.backend.modules.userauth.domain.entity.AuthToken
import dev.pango.apollo.backend.modules.userauth.domain.entity.User
import dev.pango.apollo.backend.modules.userauth.domain.failure.UserDomainFailure
import dev.pango.apollo.backend.modules.userauth.mapper.toAuthTokenDTO
import dev.pango.apollo.backend.modules.userauth.mapper.toUser
import dev.pango.apollo.backend.modules.userauth.mapper.toUserDTO
import dev.pango.apollo.backend.modules.userauth.mapper.toUserListDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.AuthTokenDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.TokensDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.LoginUserDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.RegisterUserDTO
import dev.pango.apollo.backend.modules.userauth.presentation.user.apirest.dto.UserDTO
import java.util.UUID

class UserServiceFacade(
    private val userService: UserApplicationService,
    private val authService: AuthApplicationService,
) {

    fun listAllUsers(): Either<UserDomainFailure, List<UserDTO>> {
        return userService.listAllUsers().map {
            it.toUserListDTO()
        }
    }

    fun listUserById(id: UUID): Either<UserDomainFailure, UserDTO> {
        return userService.listUserById(id).map {
            it.toUserDTO()
        }
    }

    fun createUser(registerUserDTO: RegisterUserDTO): Either<UserDomainFailure, UserDTO> {
        return userService.createUser(registerUserDTO.toUser()).map { it.toUserDTO() }
    }

    fun deleteUser(id: UUID): Either<UserDomainFailure, Unit> {
        return userService.deleteUser(id)
    }

    fun updateUser(user: User): Either<UserDomainFailure, UserDTO> {
//        return  userService.updateUser(updateUserDTO.toUser()).map {
//            it.toUserDTO()
//        }
        return userService.updateUser(user).map {
            it.toUserDTO()
        }
    }

    fun loginUser(loginUserDTO: LoginUserDTO): Either<UserDomainFailure, TokensDTO> {
        return authService.loginUser(loginUserDTO.email, loginUserDTO.password).map {
            it.toAuthTokenDTO()
        }
    }

    fun accessUser(refreshToken: String): Either<UserDomainFailure, AuthTokenDTO> {
        return authService.accessUser(refreshToken).map {
            it.toAuthTokenDTO()
        }
    }



}

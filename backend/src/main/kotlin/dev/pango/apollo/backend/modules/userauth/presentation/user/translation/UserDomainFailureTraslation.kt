package dev.pango.apollo.backend.modules.userauth.presentation.user.translation

import AuthUser
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.intl.*
import dev.pango.apollo.backend.modules.userauth.domain.failure.*
import java.util.Locale

fun UserDomainFailure.toTranslatableFailure(locale: Locale): TranslatableFailure =
    when (this) {
        UserDomainFailure.UserListNotAvailable -> TranslatableFailure(AuthUser.failureUserListNotAvailable(locale))
        UserDomainFailure.UserByIdNotAvailable -> TranslatableFailure(AuthUser.failureUserByIdNotAvailable(locale))
        UserDomainFailure.UserRegisterNotAvailable -> TranslatableFailure(AuthUser.failureUserRegisterNotAvailable(locale))
        UserDomainFailure.UserDeleteNotAvailable -> TranslatableFailure(AuthUser.failureUserDeleteNotAvailable(locale))
        UserDomainFailure.UserUpdateNotAvailable -> TranslatableFailure(AuthUser.failureUserUpdateNotAvailable(locale))
        UserDomainFailure.UserAuthNotAvailable -> TranslatableFailure(AuthUser.failureAuthNotAvailable(locale))
        UserDomainFailure.RefreshTokenNotAvailable -> TranslatableFailure(AuthUser.failureUserUpdateNotAvailable(locale))
        UserDomainFailure.UserRefreshTokenNotAvailable -> TranslatableFailure(AuthUser.failureRefreshTokenNotAvailable(locale))

        UserDomainFailure.UserFoundByTokenNotAvailable -> TranslatableFailure(AuthUser.failureFoundUserByTokenNotAvailable(locale))
        UserDomainFailure.UserFoundByEmailNotAvailable -> TranslatableFailure(AuthUser.failureFoundUserByEmailNotAvailable(locale))
        UserDomainFailure.DecodedRefreshTokenNotAvailable -> TranslatableFailure(AuthUser.failureDecodedRefreshTokenNotAvailable(locale))
        UserDomainFailure.EmailDecodedFoundNotAvailable -> TranslatableFailure(AuthUser.failureEmailDecodedFoundTokenNotAvailable(locale))
        UserDomainFailure.TokenNotFoundOrExpiredNotAvailable -> TranslatableFailure(AuthUser.failureTokenFoundOrExpired(locale))
    }

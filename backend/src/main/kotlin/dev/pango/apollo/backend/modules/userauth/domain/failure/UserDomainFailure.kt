package dev.pango.apollo.backend.modules.userauth.domain.failure

import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*

sealed class UserDomainFailure : Failure {
    data object UserListNotAvailable : UserDomainFailure()

    data object UserByIdNotAvailable : UserDomainFailure()

    data object UserRegisterNotAvailable : UserDomainFailure()

    data object UserDeleteNotAvailable : UserDomainFailure()

    data object UserUpdateNotAvailable : UserDomainFailure()

    data object UserAuthNotAvailable : UserDomainFailure()

    data object RefreshTokenNotAvailable : UserDomainFailure()

    data object UserRefreshTokenNotAvailable : UserDomainFailure()

    data object UserFoundByTokenNotAvailable : UserDomainFailure()

    data object UserFoundByEmailNotAvailable : UserDomainFailure()

    data object DecodedRefreshTokenNotAvailable : UserDomainFailure()

    data object EmailDecodedFoundNotAvailable : UserDomainFailure()

    data object TokenNotFoundOrExpiredNotAvailable : UserDomainFailure()
}

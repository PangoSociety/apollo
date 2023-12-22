package dev.pango.apollo.backend.modules.educational.domain.failure

import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*

sealed class CourseDomainFailure : Failure {
    data object DataAccessNotAccessible : CourseDomainFailure()
}
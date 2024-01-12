package dev.pango.apollo.backend.modules.educational.domain.failure

import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*

sealed class CourseDomainFailure : Failure {
    data object CourseListNotAvailable : CourseDomainFailure()

    data object CourseDeleteNotAvailable : CourseDomainFailure()

    data object CourseUpdateNotAvailable : CourseDomainFailure()
}

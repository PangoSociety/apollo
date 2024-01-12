package dev.pango.apollo.backend.modules.educational.presentation.course.translation

import Educational
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.infraestructure.intl.*
import java.util.*

fun CourseDomainFailure.toTranslatableFailure(locale: Locale): TranslatableFailure =
    when (this) {
        CourseDomainFailure.CourseListNotAvailable -> TranslatableFailure(Educational.failureCourseListNotAvailable(locale))
        CourseDomainFailure.CourseDeleteNotAvailable -> TranslatableFailure(Educational.failureCourseDeleteNotAvailable(locale))
        CourseDomainFailure.CourseUpdateNotAvailable -> TranslatableFailure(Educational.failureCourseUpdateNotAvailable(locale))
    }

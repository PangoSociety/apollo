package dev.pango.apollo.backend.modules.educational.presentation.course.translation

import MyMessages
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.failure.*
import java.util.*

fun CourseDomainFailure.toTranslatableFailure(locale: Locale): TranslatableFailure = when (this) {
    CourseDomainFailure.DataAccessNotAccessible -> TranslatableFailure(MyMessages.dataAccessNotAccessible(locale))
}

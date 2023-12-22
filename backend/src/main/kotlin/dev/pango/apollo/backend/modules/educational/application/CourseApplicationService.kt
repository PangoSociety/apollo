package dev.pango.apollo.backend.modules.educational.application

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.educational.domain.usecase.*

/**
 * This class contains all use cases related to courses
 */
class CourseApplicationService(
    private val showCourseListUseCase: ShowCourseListUseCase
) {

    fun listAllCourses(): Either<CourseDomainFailure, List<Course>> {
        return showCourseListUseCase()
    }
}
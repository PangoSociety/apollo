package dev.pango.apollo.backend.modules.educational.domain.usecase

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.educational.domain.repository.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*

class RegisterCourseUseCase(
    private val courseRepository: CourseRepository,
) {
    operator fun invoke(course: Course): Either<CourseDomainFailure, Course> {
        return courseRepository.createCourse(course).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> CourseDomainFailure.CourseListNotAvailable
            }
        }
    }
}

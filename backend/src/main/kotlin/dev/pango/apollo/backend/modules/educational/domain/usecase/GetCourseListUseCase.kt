package dev.pango.apollo.backend.modules.educational.domain.usecase

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.educational.domain.repository.*

class ShowCourseListUseCase(
    private val courseRepository: CourseRepository
) {

    operator fun invoke(): Either<CourseDomainFailure, List<Course>> {
        return courseRepository.getCourseList().mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> CourseDomainFailure.DataAccessNotAccessible
            }
        }
    }
}
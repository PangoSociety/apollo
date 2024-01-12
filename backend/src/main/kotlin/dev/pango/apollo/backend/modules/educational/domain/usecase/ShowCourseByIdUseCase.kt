package dev.pango.apollo.backend.modules.educational.domain.usecase

import arrow.core.Either
import dev.pango.apollo.backend.modules.educational.domain.entity.Course
import dev.pango.apollo.backend.modules.educational.domain.failure.CourseDomainFailure
import dev.pango.apollo.backend.modules.educational.domain.repository.CourseRepository
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.RepositoryFailure
import java.util.UUID

class ShowCourseByIdUseCase(
    private val courseRepository: CourseRepository,
) {
    operator fun invoke(id: UUID): Either<CourseDomainFailure, Course> {
        return courseRepository.getCourseById(id).mapLeft {
            when (it) {
                is RepositoryFailure.DataSourceAccessException -> CourseDomainFailure.CourseListNotAvailable
            }
        }
    }
}

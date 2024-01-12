package dev.pango.apollo.backend.modules.educational.application

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.educational.domain.usecase.*
import java.util.UUID

/**
 * This class contains all use cases related to courses
 */
class CourseApplicationService(
    private val showCourseListUseCase: ShowCourseListUseCase,
    private val showCourseByIdUseCase: ShowCourseByIdUseCase,
    private val registerCourseUseCase: RegisterCourseUseCase,
    private val deleteCourseUseCase: DeleteCourseUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase,
) {
    fun listAllCourses(): Either<CourseDomainFailure, List<Course>> {
        return showCourseListUseCase()
    }

    fun listCourseById(id: UUID): Either<CourseDomainFailure, Course> {
        return showCourseByIdUseCase(id)
    }

    fun createCourse(course: Course): Either<CourseDomainFailure, Course> {
        return registerCourseUseCase(course)
    }

    fun deleteCourse(id: UUID): Either<CourseDomainFailure, Unit> {
        return deleteCourseUseCase(id)
    }

    fun updateCourse(course: Course): Either<CourseDomainFailure, Course> {
        return updateCourseUseCase(course)
    }
}

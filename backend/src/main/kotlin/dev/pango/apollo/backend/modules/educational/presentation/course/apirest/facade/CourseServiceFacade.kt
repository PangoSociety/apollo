package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.facade

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.application.*
import dev.pango.apollo.backend.modules.educational.domain.entity.Course
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.educational.mapper.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto.*
import java.util.UUID

class CourseServiceFacade(
    private val courseService: CourseApplicationService,
) {
    fun listAllCourses(): Either<CourseDomainFailure, List<CourseDTO>> {
        return courseService.listAllCourses().map { it.toCourseDTOList() }
    }

    fun listCourseById(id: UUID): Either<CourseDomainFailure, CourseDTO> {
        return courseService.listCourseById(id).map { it.toCourseDTO() }
    }

    fun createCourse(registerCourseDTO: RegisterCourseDTO): Either<CourseDomainFailure, CourseDTO> {
        return courseService.createCourse(registerCourseDTO.toCourse()).map { it.toCourseDTO() }
    }

    fun deleteCourse(id: UUID): Either<CourseDomainFailure, Unit> {
        return courseService.deleteCourse(id)
    }

    fun updateCourse(course: Course): Either<CourseDomainFailure, CourseDTO> {
        return courseService.updateCourse(course).map {
            it.toCourseDTO()
        }
    }
}

package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.facade

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.application.*
import dev.pango.apollo.backend.modules.educational.domain.failure.*
import dev.pango.apollo.backend.modules.educational.mapper.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto.*

class CourseServiceFacade(
    private val courseService: CourseApplicationService,
) {
    fun listAllCourses(): Either<CourseDomainFailure, List<CourseDTO>> {
        return courseService.listAllCourses().map { it.toCourseDTOList() }
    }

    fun createCourse(registerCourseDTO: RegisterCourseDTO): Either<CourseDomainFailure, CourseDTO> {
        return courseService.createCourse(registerCourseDTO.toCourse()).map { it.toCourseDTO() }
    }
}

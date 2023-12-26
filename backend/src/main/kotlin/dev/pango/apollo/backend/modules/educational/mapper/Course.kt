package dev.pango.apollo.backend.modules.educational.mapper

import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto.*

fun Course.toCourseDTO() =
    CourseDTO(
        id = id,
        name = name,
    )

fun List<Course>.toCourseDTOList() = map { it.toCourseDTO() }

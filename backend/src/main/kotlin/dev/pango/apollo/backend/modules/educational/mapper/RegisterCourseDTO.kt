package dev.pango.apollo.backend.modules.educational.mapper

import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto.*

fun RegisterCourseDTO.toCourse() =
    Course(
        name = name,
    )

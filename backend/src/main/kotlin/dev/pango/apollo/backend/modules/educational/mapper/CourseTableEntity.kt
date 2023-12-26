package dev.pango.apollo.backend.modules.educational.mapper

import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.infraestructure.persistence.entity.*

fun CourseTableEntity.toCourse() =
    Course(
        id = id.value,
        name = name,
    )

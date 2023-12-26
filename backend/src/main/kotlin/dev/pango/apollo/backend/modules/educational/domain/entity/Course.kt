package dev.pango.apollo.backend.modules.educational.domain.entity

import java.util.*

data class Course(
    val id: UUID = UUID.randomUUID(),
    val name: String,
)

package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto

import kotlinx.serialization.*

@Serializable
data class RegisterCourseDTO(
    @SerialName("name")
    val name: String,
)

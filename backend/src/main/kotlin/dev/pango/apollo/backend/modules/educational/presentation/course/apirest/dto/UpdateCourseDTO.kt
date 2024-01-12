package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateCourseDTO(
    @SerialName("name")
    val name: String,
)

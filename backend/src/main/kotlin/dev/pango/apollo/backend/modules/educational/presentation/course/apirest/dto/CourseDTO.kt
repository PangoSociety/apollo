package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto

import kotlinx.serialization.*

@Serializable
data class CourseDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
)
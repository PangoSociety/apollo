package dev.pango.apollo.backend.modules.educational.presentation.course.apirest.dto

import dev.pango.apollo.backend.framework.serialization.*
import kotlinx.serialization.*
import java.util.*

@Serializable
data class CourseDTO(
    @SerialName("id")
    @Serializable(UUIDSerializer::class)
    val id: UUID,
    @SerialName("name")
    val name: String,
)

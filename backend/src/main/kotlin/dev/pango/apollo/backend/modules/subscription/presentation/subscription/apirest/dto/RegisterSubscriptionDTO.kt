package dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto

import dev.pango.apollo.backend.framework.serialization.*
import kotlinx.serialization.*
import org.jetbrains.exposed.dao.id.*
import java.time.LocalDate
import java.util.UUID

data class RegisterSubscriptionDTO(
    @SerialName("course")
    @Serializable(UUIDSerializer::class)
    val courseId: UUID,
    @Serializable(DateSerializer::class)
    @SerialName("start")
    val startDate: LocalDate,
    @Serializable(DateSerializer::class)
    @SerialName("end")
    val endDate: LocalDate,
)

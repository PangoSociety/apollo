package dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto

import dev.pango.apollo.backend.framework.serialization.DateSerializer
import dev.pango.apollo.backend.framework.serialization.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.UUID

@Serializable
data class UpdateSubscriptionDTO(
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

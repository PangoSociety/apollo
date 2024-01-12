package dev.pango.apollo.backend.modules.subscription.presentation.subscription.apirest.dto

import dev.pango.apollo.backend.framework.serialization.*
import kotlinx.serialization.*
import org.jetbrains.exposed.dao.id.*
import java.time.*
import java.util.*

@Serializable
data class SubscriptionDTO(
    @SerialName("id")
    @Serializable(UUIDSerializer::class)
    val id: UUID,
//    @SerialName("user")
//    @Serializable(UUIDSerializer::class)
//    val userId: UUID,
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

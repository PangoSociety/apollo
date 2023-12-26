package dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.dto

import kotlinx.serialization.*

@Serializable
data class FailureDTO(
    val message: String,
)

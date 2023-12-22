package dev.pango.apollo.backend.modules.sharedkernel.mapper

import dev.pango.apollo.backend.modules.sharedkernel.presentation.apirest.dto.*

fun String.toFailureDTO() = FailureDTO(
    message = this
)

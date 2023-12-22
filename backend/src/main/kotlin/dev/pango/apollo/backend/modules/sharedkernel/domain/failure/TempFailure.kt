package dev.pango.apollo.backend.modules.sharedkernel.domain.failure

interface Failure

class TranslatableFailure(val i18nMessage: String) : Failure

package dev.pango.apollo.backend.modules.sharedkernel.domain.repository

sealed interface RepositoryFailure {
    data class DataSourceAccessException(val throwable: Throwable) : RepositoryFailure
}

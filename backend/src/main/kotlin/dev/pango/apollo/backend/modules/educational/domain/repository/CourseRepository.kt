package dev.pango.apollo.backend.modules.educational.domain.repository

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*

sealed interface RepositoryFailure {
    data class DataSourceAccessException(val throwable: Throwable) : RepositoryFailure
}

interface CourseRepository {

    fun getCourseList(): Either<RepositoryFailure, List<Course>>
}
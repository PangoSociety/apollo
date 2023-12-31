package dev.pango.apollo.backend.modules.educational.domain.repository

import arrow.core.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.repository.sorting.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.filtering.*

interface CourseRepository {
    fun getCourseList(
        sorting: CourseSorting? = null,
        pagination: ListPagination? = null,
    ): Either<RepositoryFailure, List<Course>>

    fun createCourse(course: Course): Either<RepositoryFailure, Course>
}

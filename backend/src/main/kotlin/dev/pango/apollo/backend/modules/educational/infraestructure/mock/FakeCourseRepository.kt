package dev.pango.apollo.backend.modules.educational.infraestructure.mock

import arrow.core.*
import dev.pango.apollo.backend.framework.functional.*
import dev.pango.apollo.backend.framework.testing.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.repository.*
import dev.pango.apollo.backend.modules.educational.domain.repository.sorting.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.filtering.*
import io.github.serpro69.kfaker.*
import kotlin.random.*

class FakeCourseRepository : CourseRepository {
    private val faker = Faker()
    private val defaultInitCourses = List(10) { Course(name = faker.educator.courseName()) }
    private val courses = mutableListOf(*defaultInitCourses.toTypedArray())

    override fun getCourseList(
        sorting: CourseSorting?,
        pagination: ListPagination?,
    ): Either<RepositoryFailure, List<Course>> =
        Either.catch {
            if (Random.generateDecision(0.2)) {
                throw RuntimeException("Data access not accessible")
            }
            courses
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun createCourse(course: Course): Either<RepositoryFailure, Course> =
        Either.catch {
            courses.add(course)
            course
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }
}

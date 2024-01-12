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
import java.util.UUID
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

    override fun getCourseById(id: UUID): Either<RepositoryFailure, Course> =
        Either.catch {
            val course = courses.find { it.id == id }!!
            course
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

    override fun deleteCourse(id: UUID): Either<RepositoryFailure, Unit> {
        return Either.catch {
            courses.removeIf {
                it.id == id
            }
            Unit
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }
    }

    override fun updateCourse(course: Course): Either<RepositoryFailure, Course> {
        return Either.catch {
            val updateCourse =
                courses.find {
                    it.id == course.id
                }
            updateCourse?.apply {
                name = course.name
            }
            course
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }
    }
}

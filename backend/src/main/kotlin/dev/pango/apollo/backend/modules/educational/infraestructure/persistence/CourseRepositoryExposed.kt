package dev.pango.apollo.backend.modules.educational.infraestructure.persistence

import arrow.core.*
import dev.pango.apollo.backend.framework.functional.*
import dev.pango.apollo.backend.modules.educational.domain.entity.*
import dev.pango.apollo.backend.modules.educational.domain.repository.*
import dev.pango.apollo.backend.modules.educational.domain.repository.sorting.*
import dev.pango.apollo.backend.modules.educational.infraestructure.persistence.entity.*
import dev.pango.apollo.backend.modules.educational.mapper.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.*
import dev.pango.apollo.backend.modules.sharedkernel.domain.repository.filtering.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.*
import java.util.UUID

class CourseRepositoryExposed : CourseRepository {
    override fun getCourseList(
        sorting: CourseSorting?,
        pagination: ListPagination?,
    ): Either<RepositoryFailure, List<Course>> =
        Either.catch {
            transaction {
                CourseTableEntity.all().map { it.toCourse() }
            }
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun getCourseById(id: UUID): Either<RepositoryFailure, Course> =
        Either.catch {
            transaction {
                CourseTableEntity[id].toCourse()
            }
        }.mapLeft {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun createCourse(course: Course): Either<RepositoryFailure, Course> =
        Either.catch {
            val newCourse =
                transaction {
                    CourseTableEntity.new(course.id) {
                        name = course.name
                    }
                }
            newCourse.toCourse()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun deleteCourse(id: UUID): Either<RepositoryFailure, Unit> =
        Either.catch {
            transaction {
                CourseTableEntity.findById(id)?.delete()
            }
            Unit
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }

    override fun updateCourse(course: Course): Either<RepositoryFailure, Course> =
        Either.catch {
            transaction {
                val courseEntity = CourseTableEntity.findById(EntityID(course.id, CourseTable))!!
                courseEntity.name = course.name
                courseEntity
            }.toCourse()
        }.mapLeftLogged {
            RepositoryFailure.DataSourceAccessException(it)
        }
}

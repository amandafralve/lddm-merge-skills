package com.example.lddm_merge_skills.db

import com.example.lddm_merge_skills.model.Course
import com.example.lddm_merge_skills.repository.CourseRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

class ExposedCourseRepository: CourseRepository {
    private fun ResultRow.toCourse() = Course(
        id = this[Courses.id].value,
        title = this[Courses.title],
        description = this[Courses.description],
        icon = this[Courses.icon],
        color = this[Courses.color],
        totalLessons = this[Courses.totalLessons],
    )

    override suspend fun getAll(): List<Course> = newSuspendedTransaction {
        Courses.selectAll().map { it.toCourse() }
    }

    override suspend fun getById(id: Int): Course? = newSuspendedTransaction {
        Courses.selectAll()
            .where { Courses.id eq id }
            .map { it.toCourse() }
            .singleOrNull()
    }

    override suspend fun create(course: Course): Course = newSuspendedTransaction {
        val insertStatement = Courses.insert {
            it[title] = course.title
            it[description] = course.description
            it[icon] = course.icon
            it[color] = course.color
            it[totalLessons] = course.totalLessons
        }
        insertStatement.resultedValues!!.first().toCourse()
    }

    override suspend fun update(id: Int, course: Course): Course  = newSuspendedTransaction {
        Courses.update({ Courses.id eq id }) {
            id[title] = course.title
            id[description] = course.description
            id[icon] = course.icon
            id[color] = course.color
            id[totalLessons] = course.totalLessons
        }

        Courses.selectAll()
            .where { Courses.id eq id }
            .map { it.toCourse() }
            .single()
    }

    override suspend fun delete(id: Int): Unit = newSuspendedTransaction {
        Courses.deleteWhere { Courses.id eq id }
    }
}
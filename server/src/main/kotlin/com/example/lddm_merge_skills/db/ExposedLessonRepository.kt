package com.example.lddm_merge_skills.db

import com.example.lddm_merge_skills.db.dto.toInsertDTO
import com.example.lddm_merge_skills.db.dto.toLesson
import com.example.lddm_merge_skills.model.Lesson
import com.example.lddm_merge_skills.repository.LessonRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

class ExposedLessonRepository : LessonRepository {
    override suspend fun getByCourseId(courseId: Int): List<Lesson> = newSuspendedTransaction {
        Lessons.selectAll().where { Lessons.courseId eq courseId }.orderBy(Lessons.order).map { it.toLesson() }
    }
    override suspend fun getById(id: Int): Lesson? = newSuspendedTransaction {
        Lessons.selectAll().where { Lessons.id eq id }.map { it.toLesson() }.singleOrNull()
    }
    override suspend fun create(lesson: Lesson): Lesson = newSuspendedTransaction {
        val insertStatement = Lessons.insert { lesson.toInsertDTO().applyTo(it) }
        insertStatement.resultedValues!!.first().toLesson()
    }
    override suspend fun update(id: Int, lesson: Lesson): Lesson = newSuspendedTransaction {
        Lessons.update({ Lessons.id eq id }) { lesson.toInsertDTO().applyTo(it) }
        getById(id) ?: lesson
    }
    override suspend fun delete(id: Int) {
        newSuspendedTransaction {
            Lessons.deleteWhere { Lessons.id eq id }
        }
    }
}
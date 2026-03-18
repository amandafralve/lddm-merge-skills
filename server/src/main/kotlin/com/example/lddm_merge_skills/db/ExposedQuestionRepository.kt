package com.example.lddm_merge_skills.db

import com.example.lddm_merge_skills.db.dto.toInsertDTO
import com.example.lddm_merge_skills.db.dto.toQuestion
import com.example.lddm_merge_skills.model.Question
import com.example.lddm_merge_skills.repository.QuestionRepository
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update


class ExposedQuestionRepository : QuestionRepository {
    override suspend fun getByLessonId(lessonId: Int): List<Question> = newSuspendedTransaction {
        Questions.selectAll().where { Questions.lessonId eq lessonId }.orderBy(Questions.order).map { it.toQuestion() }
    }
    override suspend fun getById(id: Int): Question? = newSuspendedTransaction {
        Questions.selectAll().where { Questions.id eq id }.map { it.toQuestion() }.singleOrNull()
    }
    override suspend fun create(question: Question): Question = newSuspendedTransaction {
        val insertStatement = Questions.insert { question.toInsertDTO().applyTo(it) }
        insertStatement.resultedValues!!.first().toQuestion()
    }
    override suspend fun update(id: Int, question: Question): Question = newSuspendedTransaction {
        Questions.update({ Questions.id eq id }) { question.toInsertDTO().applyTo(it) }
        getById(id) ?: question
    }
    override suspend fun delete(id: Int) {
        newSuspendedTransaction {
            Questions.deleteWhere { Questions.id eq id }
        }
    }
}
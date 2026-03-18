package com.example.lddm_merge_skills.routes

import com.example.lddm_merge_skills.repository.CourseRepository
import com.example.lddm_merge_skills.repository.LessonRepository
import io.ktor.http.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseRoutes(
    courseRepository: CourseRepository,
    lessonRepository: LessonRepository
) {
    // GET /courses → Lista todos os cursos
    get("/courses") {
        val courses = courseRepository.getAll()
        call.respond(courses)
    }

    // GET /courses/{id}/lessons → Lições de um curso
    get("/courses/{id}/lessons") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to "ID inválido"))
            return@get
        }
        val lessons = lessonRepository.getByCourseId(id)
        call.respond(lessons)
    }

    // POST /COURSES
    post("/courses"){
        try {
            val courseRequest = call.receive<com.example.lddm_merge_skills.model.Course>()
            val createdCourse = courseRepository.create(courseRequest)
            call.respond(HttpStatusCode.Created, createdCourse)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Formato de curso inválido"))
        }
    }

    //
}
package com.example.lddm_merge_skills

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.lddm_merge_skills.db.DatabaseFactory
import com.example.lddm_merge_skills.db.ExposedCourseRepository
import com.example.lddm_merge_skills.db.ExposedLessonRepository
import com.example.lddm_merge_skills.db.ExposedQuestionRepository
import com.example.lddm_merge_skills.routes.courseRoutes
import com.example.lddm_merge_skills.routes.lessonRoutes
import com.example.lddm_merge_skills.routes.questionRoutes
import com.fatec.lddm_merge_skills.routes.progressRoutes
import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.plugins.swagger.swaggerUI



fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val dotenv = dotenv() // Carrega o arquivo .env obrigatoriamente

    // Exemplo de leitura direta
    val useSupabase = dotenv["USE_SUPABASE"]?.toBoolean() ?: false

    install(ContentNegotiation) {
        json()
    }

    //  Tratamento centralizado de erros
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf("error" to (cause.message ?: "Erro interno"))
            )
        }
    }

    if (!useSupabase) {
        DatabaseFactory.init() // Engine Local
    }

    //  Instanciando os repositórios (Exposed = banco real)
    val courseRepository = ExposedCourseRepository()
    val lessonRepository = ExposedLessonRepository()
    val questionRepository = ExposedQuestionRepository()

    routing {
        get("/") { call.respondText("Ktor: ${Greeting().greet()}") }
        get("/health") { call.respondText("OK") }

        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")

        //  Semana 03: Rotas da API
        courseRoutes(courseRepository, lessonRepository)
        lessonRoutes(questionRepository)
        questionRoutes(questionRepository)
        progressRoutes(questionRepository)
    }
}
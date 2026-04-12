package com.atv1.at1_t1_kmp_ktor.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.atv1.at1_t1_kmp_ktor.model.Plant
import com.atv1.at1_t1_kmp_ktor.repository.PlantRepository

fun Route.plantRoutes(repository: PlantRepository) {
    route("/plants") {
        get {
            call.respond(repository.findAll())
        }

        post {
            val plant = call.receive<Plant>()
            call.respond(HttpStatusCode.Created, repository.create(plant))
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest, "Id inválido")

            val plant = call.receive<Plant>()
            if (repository.update(id, plant)) {
                call.respond(HttpStatusCode.OK, "Planta atualizada")
            } else {
                call.respond(HttpStatusCode.NotFound, "Planta não encontrada")
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest, "Id inválido")

            if (repository.delete(id)) {
                call.respond(HttpStatusCode.OK, "Planta removida")
            } else {
                call.respond(HttpStatusCode.NotFound, "Planta não encontrada")
            }
        }
    }
}
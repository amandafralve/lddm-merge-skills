package com.atv1.at1_t1_kmp_ktor.routes

import com.atv1.at1_t1_kmp_ktor.model.Plant
import com.atv1.at1_t1_kmp_ktor.repository.PlantRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.plantRoutes(repository: PlantRepository) {
    route("/plants") {
        get {
            call.respond(repository.getAll())
        }

        post {
            val plant = call.receive<Plant>()
            val created = repository.create(plant)
            call.respond(HttpStatusCode.Created, created)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest, "ID inválido")

            val plant = call.receive<Plant>()
            val updated = repository.update(id, plant)
            call.respond(HttpStatusCode.OK, updated)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido")

            repository.delete(id)
            call.respond(HttpStatusCode.OK, "Planta removida com sucesso")
        }

        get {
            try {
                println("Entrou na rota /plants")
                val plants = repository.getAll()
                println("Buscou plantas: $plants")
                call.respond(plants)
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(
                    io.ktor.http.HttpStatusCode.InternalServerError,
                    "Erro interno: ${e.message}"
                )
            }
        }
    }
}
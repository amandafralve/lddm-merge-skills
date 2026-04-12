package com.atv1.at1_t1_kmp_ktor.routes

import com.atv1.at1_t1_kmp_ktor.model.Species
import com.atv1.at1_t1_kmp_ktor.repository.SpeciesRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.speciesRoutes(repository: SpeciesRepository) {
    route("/species") {
        get {
            call.respond(repository.getAll())
        }

        post {
            val species = call.receive<Species>()
            val created = repository.create(species)
            call.respond(HttpStatusCode.Created, created)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest, "ID inválido")

            val species = call.receive<Species>()
            val updated = repository.update(id, species)
            call.respond(HttpStatusCode.OK, updated)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido")

            repository.delete(id)
            call.respond(HttpStatusCode.OK, "Espécie removida com sucesso")
        }
    }
}
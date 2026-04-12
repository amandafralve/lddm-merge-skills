package com.atv1.at1_t1_kmp_ktor

import com.atv1.at1_t1_kmp_ktor.db.DatabaseFactory
import com.atv1.at1_t1_kmp_ktor.db.ExposedPlantRepository
import com.atv1.at1_t1_kmp_ktor.db.ExposedSpeciesRepository
import com.atv1.at1_t1_kmp_ktor.routes.plantRoutes
import com.atv1.at1_t1_kmp_ktor.routes.speciesRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*



fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    install(ContentNegotiation) {
        json()
    }

    val plantRepository = ExposedPlantRepository()
    val speciesRepository = ExposedSpeciesRepository()

    routing {


        get("/") {
            call.respondRedirect("/swagger")
        }

        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
        openAPI(path = "openapi", swaggerFile = "openapi/documentation.yaml")

        plantRoutes(plantRepository)
        speciesRoutes(speciesRepository)

        plantRoutes(plantRepository)
        speciesRoutes(speciesRepository)
    }

}


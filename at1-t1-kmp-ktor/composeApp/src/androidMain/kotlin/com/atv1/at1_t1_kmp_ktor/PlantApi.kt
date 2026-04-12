package com.atv1.at1_t1_kmp_ktor

import com.atv1.at1_t1_kmp_ktor.model.Plant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

private val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun fetchPlants(): List<Plant> {
    return httpClient
        .get("http://192.168.1.12:8080/plants")
        .body()
}
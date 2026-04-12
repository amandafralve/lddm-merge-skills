package com.atv1.at1_t1_kmp_ktor

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText


expect fun createHttpClient(): HttpClient

class ApiClient {
    private val client = createHttpClient()
    private val baseUrl = "http://${serverHost()}:$SERVER_PORT" // Verifique a porta do seu server

    suspend fun hello(): String = client.get("$baseUrl/hello").bodyAsText()

    suspend fun echo(text: String): String = client.post("$baseUrl/echo") {
        setBody(text)
    }.bodyAsText()
}
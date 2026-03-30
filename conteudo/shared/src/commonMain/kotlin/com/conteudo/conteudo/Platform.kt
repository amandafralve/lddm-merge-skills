package com.conteudo.conteudo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
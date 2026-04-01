package com.conteudo.a01_bd_migration

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
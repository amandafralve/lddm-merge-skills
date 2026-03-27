package com.aula.lddm_conteudo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
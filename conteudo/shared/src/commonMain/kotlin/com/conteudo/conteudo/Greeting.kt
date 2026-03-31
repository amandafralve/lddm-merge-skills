package com.conteudo.conteudo

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    val couseName = "LDDM"

    fun sum(a: Int, b: Int): Int = a + b

    class Aluno(val nome: String, val idade: Int){
        fun resumo(): String = "$nome ($idade)"
    }
}
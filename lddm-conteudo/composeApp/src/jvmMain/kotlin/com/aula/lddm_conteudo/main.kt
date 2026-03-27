package com.aula.lddm_conteudo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "lddm_conteudo",
    ) {
        App()
    }
}
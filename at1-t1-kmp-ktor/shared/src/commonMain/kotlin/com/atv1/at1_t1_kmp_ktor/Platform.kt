package com.atv1.at1_t1_kmp_ktor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
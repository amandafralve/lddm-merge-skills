package com.example.lddm_merge_skills

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
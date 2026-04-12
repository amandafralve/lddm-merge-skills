package com.atv1.at1_t1_kmp_ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class Species(
    val id: Long? = null,
    val name: String
)
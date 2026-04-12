package com.atv1.at1_t1_kmp_ktor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Plant(
    val id: Long? = null,
    val scientificName: String,
    val popularName: String,
    val speciesId: Long,
    val luminosity: String,
    @SerialName("total_plants")
    val totalPlants: Int? = null
)
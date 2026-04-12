package com.atv1.at1_t1_kmp_ktor.repository

import com.atv1.at1_t1_kmp_ktor.model.Plant

interface PlantRepository {
    suspend fun getAll(): List<Plant>
    suspend fun getById(id: Long): Plant?
    suspend fun create(plant: Plant): Plant
    suspend fun update(id: Long, plant: Plant): Plant
    suspend fun delete(id: Long)
}
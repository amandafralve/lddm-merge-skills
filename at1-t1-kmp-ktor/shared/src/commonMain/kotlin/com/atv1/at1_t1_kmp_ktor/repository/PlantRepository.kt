package com.atv1.at1_t1_kmp_ktor.repository

import com.atv1.at1_t1_kmp_ktor.model.Plant

interface PlantRepository {
    suspend fun getAll(): List<Plant>
    suspend fun getById(id: Int): Plant?
    suspend fun findAll(): List<Plant>
    suspend fun create(plant: Plant): Plant
    suspend fun update(id: Long, plant: Plant): Boolean
    suspend fun delete(id: Long): Boolean
}
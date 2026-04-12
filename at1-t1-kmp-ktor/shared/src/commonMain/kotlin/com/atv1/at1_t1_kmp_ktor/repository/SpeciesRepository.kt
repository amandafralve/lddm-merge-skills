package com.atv1.at1_t1_kmp_ktor.repository

import com.atv1.at1_t1_kmp_ktor.model.Species

interface SpeciesRepository {
    suspend fun findAll(): List<Species>
    suspend fun create(species: Species): Species
    suspend fun update(id: Long, species: Species): Boolean
    suspend fun delete(id: Long): Boolean
}
package com.atv1.at1_t1_kmp_ktor.db

import com.atv1.at1_t1_kmp_ktor.model.Plant
import com.atv1.at1_t1_kmp_ktor.repository.PlantRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedPlantRepository : PlantRepository {

    override suspend fun getAll(): List<Plant> = newSuspendedTransaction {
        PlantsTable.selectAll().map { it.toPlant() }
    }

    override suspend fun getById(id: Long): Plant? = newSuspendedTransaction {
        PlantsTable.selectAll()
            .where { PlantsTable.id eq id }
            .map { it.toPlant() }
            .singleOrNull()
    }

    override suspend fun create(plant: Plant): Plant = newSuspendedTransaction {
        val result = PlantsTable.insert {
            it[scientificName] = plant.scientificName
            it[popularName] = plant.popularName
            it[speciesId] = plant.speciesId
            it[luminosity] = plant.luminosity
            it[totalPlants] = plant.totalPlants
        }

        result.resultedValues!!.first().toPlant()
    }

    override suspend fun update(id: Long, plant: Plant): Plant = newSuspendedTransaction {
        PlantsTable.update({ PlantsTable.id eq id }) {
            it[scientificName] = plant.scientificName
            it[popularName] = plant.popularName
            it[speciesId] = plant.speciesId
            it[luminosity] = plant.luminosity
            it[totalPlants] = plant.totalPlants
        }

        getById(id) ?: plant
    }

    override suspend fun delete(id: Long) {
        newSuspendedTransaction {
            PlantsTable.deleteWhere { PlantsTable.id eq id }
        }
    }

    private fun ResultRow.toPlant() = Plant(
        id = this[PlantsTable.id],
        scientificName = this[PlantsTable.scientificName],
        popularName = this[PlantsTable.popularName],
        speciesId = this[PlantsTable.speciesId],
        luminosity = this[PlantsTable.luminosity],
        totalPlants = this[PlantsTable.totalPlants]
    )
}
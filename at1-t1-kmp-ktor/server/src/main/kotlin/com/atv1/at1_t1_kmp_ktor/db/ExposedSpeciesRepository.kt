package com.atv1.at1_t1_kmp_ktor.db

import com.atv1.at1_t1_kmp_ktor.model.Species
import com.atv1.at1_t1_kmp_ktor.repository.SpeciesRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedSpeciesRepository : SpeciesRepository {

    override suspend fun getAll(): List<Species> = newSuspendedTransaction {
        SpeciesTable.selectAll().map { it.toSpecies() }
    }

    override suspend fun getById(id: Long): Species? = newSuspendedTransaction {
        SpeciesTable.selectAll()
            .where { SpeciesTable.id eq id }
            .map { it.toSpecies() }
            .singleOrNull()
    }

    override suspend fun create(species: Species): Species = newSuspendedTransaction {
        val result = SpeciesTable.insert {
            it[name] = species.name
        }

        result.resultedValues!!.first().toSpecies()
    }

    override suspend fun update(id: Long, species: Species): Species = newSuspendedTransaction {
        SpeciesTable.update({ SpeciesTable.id eq id }) {
            it[name] = species.name
        }

        getById(id) ?: species
    }

    override suspend fun delete(id: Long) {
        newSuspendedTransaction {
            SpeciesTable.deleteWhere { SpeciesTable.id eq id }
        }
    }

    private fun ResultRow.toSpecies() = Species(
        id = this[SpeciesTable.id],
        name = this[SpeciesTable.name]
    )
}
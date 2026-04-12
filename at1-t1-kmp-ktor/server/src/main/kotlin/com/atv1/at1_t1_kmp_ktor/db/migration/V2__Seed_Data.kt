package com.atv1.at1_t1_kmp_ktor.db.migration

import com.atv1.at1_t1_kmp_ktor.db.PlantsTable
import com.atv1.at1_t1_kmp_ktor.db.SpeciesTable
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class V2__Seed_Data : BaseJavaMigration() {
    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect(getNewConnection = { safeConnection })

        transaction(database) {
            SpeciesTable.insert {
                it[id] = 1
                it[name] = "Monstera deliciosa"
            }

            SpeciesTable.insert {
                it[id] = 2
                it[name] = "Epipremnum aureum"
            }

            SpeciesTable.insert {
                it[id] = 3
                it[name] = "Zamioculcas zamiifolia"
            }

            SpeciesTable.insert {
                it[id] = 4
                it[name] = "Spathiphyllum wallisii"
            }

            SpeciesTable.insert {
                it[id] = 5
                it[name] = "Sansevieria trifasciata"
            }

            PlantsTable.insert {
                it[id] = 1
                it[scientificName] = "Monstera deliciosa"
                it[popularName] = "Costela-de-adão"
                it[speciesId] = 1
                it[luminosity] = "Meia sombra"
                it[totalPlants] = 10
            }

            PlantsTable.insert {
                it[id] = 2
                it[scientificName] = "Epipremnum aureum"
                it[popularName] = "Jiboia"
                it[speciesId] = 2
                it[luminosity] = "Sombra"
                it[totalPlants] = 20
            }

            PlantsTable.insert {
                it[id] = 3
                it[scientificName] = "Zamioculcas zamiifolia"
                it[popularName] = "Zamioculca"
                it[speciesId] = 3
                it[luminosity] = "Baixa luminosidade"
                it[totalPlants] = 15
            }

            PlantsTable.insert {
                it[id] = 4
                it[scientificName] = "Spathiphyllum wallisii"
                it[popularName] = "Lírio-da-paz"
                it[speciesId] = 4
                it[luminosity] = "Meia sombra"
                it[totalPlants] = 8
            }

            PlantsTable.insert {
                it[id] = 5
                it[scientificName] = "Sansevieria trifasciata"
                it[popularName] = "Espada-de-são-jorge"
                it[speciesId] = 5
                it[luminosity] = "Sol pleno"
                it[totalPlants] = 12
            }
        }
    }
}
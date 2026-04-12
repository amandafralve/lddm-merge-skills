package com.atv1.at1_t1_kmp_ktor.db.migration

import com.atv1.at1_t1_kmp_ktor.db.PlantsTable
import com.atv1.at1_t1_kmp_ktor.db.SpeciesTable
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class V1__Initial_Schema : BaseJavaMigration() {
    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect(getNewConnection = { safeConnection })

        transaction(database) {
            SchemaUtils.create(SpeciesTable, PlantsTable)
        }
    }
}
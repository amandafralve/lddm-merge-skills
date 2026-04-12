package com.atv1.at1_t1_kmp_ktor.db

import org.jetbrains.exposed.sql.Table

object SpeciesTable : Table("species") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 150)

    override val primaryKey = PrimaryKey(id)
}

object PlantsTable : Table("plants") {
    val id = long("id").autoIncrement()
    val scientificName = varchar("scientific_name", 150)
    val popularName = varchar("popular_name", 150)
    val speciesId = long("species_id").references(SpeciesTable.id)
    val luminosity = varchar("luminosity", 100)
    val totalPlants = integer("total_plants").nullable()

    override val primaryKey = PrimaryKey(id)
}
package com.atv1.at1_t1_kmp_ktor.db

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun init() {
        val dbUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/plants-db"
        val dbUser = System.getenv("DB_USER") ?: "postgres"
        val dbPassword = System.getenv("DB_PASSWORD") ?: System.getProperty("DB_PASSWORD") ?: "devpassword"

        println("Conectando ao banco: $dbUrl")

        val flyway = Flyway.configure()
            .dataSource(dbUrl, dbUser, dbPassword)
            .locations("classpath:com/atv1/at1_t1_kmp_ktor/db/migration")
            .baselineOnMigrate(true)
            .load()

        val result = flyway.migrate()
        println("Flyway executou: ${result.migrationsExecuted} migrations")

        Database.connect(
            url = dbUrl,
            driver = "org.postgresql.Driver",
            user = dbUser,
            password = dbPassword
        )
    }
}

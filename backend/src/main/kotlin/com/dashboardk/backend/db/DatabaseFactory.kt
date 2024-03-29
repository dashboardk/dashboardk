package com.dashboardk.backend.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory(
    private val dbUrl: String,
    private val dbPort: String,
    private val dbUser: String,
    private val dbPassword: String,
    private val dbName: String
) {

    private val dbHost = "jdbc:postgresql://$dbUrl:$dbPort/$dbName"

    init {
//        val flyway = Flyway.configure()
//            .dataSource(dbHost, dbUser, dbPassword)
//            .baselineOnMigrate(true)
//            .load()
//        flyway.migrate()

        Database.connect(hikari())
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                RepoTable,
                CommitTable,
                CollaboratorTable,
                PullRequestTable
            )
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = dbHost
        config.username = dbUser
        config.password = dbPassword
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}

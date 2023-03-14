package com.dashboardk.repositories

import com.dashboardk.db.RepoTable
import com.dashboardk.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction

class RepoInfoRepository {

    fun storeRepo(name: String): Flow<Repo> {
        return flowOf(
            transaction {
                RepoTable.insert {
                    it[RepoTable.name] = name
                }.let {
                    toRepo(it)
                }
            }
        )
    }

    fun getRepo(): Flow<List<Repo>> {
        return flowOf(transaction {
            RepoTable.selectAll().map {
                toRepo(it)
            }
        })
    }

    private fun toRepo(row: ResultRow): Repo {
        return Repo(name = row[RepoTable.name])
    }

    private fun toRepo(row: InsertStatement<Number>): Repo {
        return Repo(name = row[RepoTable.name])
    }
}
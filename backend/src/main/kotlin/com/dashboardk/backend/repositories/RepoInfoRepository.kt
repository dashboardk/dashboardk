package com.dashboardk.backend.repositories

import com.dashboardk.backend.db.RepoTable
import com.dashboardk.backend.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class RepoInfoRepository {

    fun storeRepo(name: String): Flow<Unit> {
        return flowOf(
            transaction {
                RepoTable.insertIgnore {
                    it[RepoTable.name] = name
                }.let {
                }
            }
        )
    }

    fun getRepos(): Flow<List<Repo>> {
        return flowOf(transaction {
            RepoTable.selectAll().map {
                toRepo(it)
            }
        })
    }

    fun getRepo(repoId: Long): Flow<Repo> {
        return flowOf(transaction {
            toRepo(RepoTable.select(where = RepoTable.id.eq(repoId)).first())
        })
    }

    fun getRepo(name: String): Flow<Repo> {
        return flowOf(transaction {
            toRepo(RepoTable.select(where = RepoTable.name.eq(name)).first())
        })
    }

    private fun toRepo(row: ResultRow): Repo {
        return Repo(id = row[RepoTable.id], name = row[RepoTable.name])
    }
}
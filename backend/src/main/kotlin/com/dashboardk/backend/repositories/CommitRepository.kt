package com.dashboardk.backend.repositories

import com.dashboardk.backend.db.CommitTable
import com.dashboardk.backend.domain.commit.Commit
import com.dashboardk.backend.dtos.GithubCommitInfoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class CommitRepository {

    fun storeCommits(repoId: Long, commitInfos: List<GithubCommitInfoDto>): Flow<Unit> {
        return flowOf(
            transaction {
                commitInfos.forEach { commitInfo ->
                    CommitTable.insertIgnore {
                        it[sha] = commitInfo.sha
                        it[message] = commitInfo.commit.message
                        it[CommitTable.repoId] = repoId
                    }
                }
            }
        )
    }

    fun storeCommit(sha: String, message: String, repoId: Long): Flow<Unit> {
        return flowOf(
            transaction {
                CommitTable.insertIgnore {
                    it[CommitTable.sha] = sha
                    it[CommitTable.message] = message
                    it[CommitTable.repoId] = repoId
                }.let { }
            }
        )
    }

    fun getCommits(repoId: Long): Flow<List<Commit>> {
        return flowOf(transaction {
            CommitTable.select(where = CommitTable.repoId.eq(repoId)).map {
                toCommit(it)
            }
        })
    }

    private fun toCommit(row: ResultRow): Commit {
        return Commit(
            id = row[CommitTable.id],
            sha = row[CommitTable.sha],
            message = row[CommitTable.message],
            repoId = row[CommitTable.repoId]
        )
    }
}
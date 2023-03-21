package com.dashboardk.backend.repositories

import com.dashboardk.backend.db.CommitTable
import com.dashboardk.backend.domain.collectors.infos.CommitInfo
import com.dashboardk.backend.domain.commit.Commit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.ZoneId

class CommitRepository(private val collaboratorRepository: CollaboratorRepository) {

    fun storeCommits(repoId: Long, commitInfos: List<CommitInfo>): Flow<Unit> {
        return flowOf(
            transaction {
                commitInfos.forEach { commitInfo ->
                    if (CommitTable.select(where = CommitTable.sha.eq(commitInfo.sha)).firstOrNull() == null) {
                        val collaborator = collaboratorRepository.getOrCreateCollaborator(
                            name = commitInfo.committedBy,
                            repoId = repoId
                        )
                        CommitTable.insert {
                            it[sha] = commitInfo.sha
                            it[message] = commitInfo.message
                            it[CommitTable.repoId] = repoId
                            it[committedBy] = collaborator.id
                            it[committedTime] = LocalDateTime.ofInstant(commitInfo.time, ZoneId.systemDefault())
                        }
                    }
                }
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
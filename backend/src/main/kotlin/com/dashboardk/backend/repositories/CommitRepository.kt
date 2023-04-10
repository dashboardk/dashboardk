package com.dashboardk.backend.repositories

import com.dashboardk.backend.db.CollaboratorTable
import com.dashboardk.backend.db.CommitTable
import com.dashboardk.backend.db.RepoTable
import com.dashboardk.backend.domain.collectors.infos.CommitInfo
import com.dashboardk.backend.domain.commit.Commit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.ZoneId

class CommitRepository(private val collaboratorRepository: CollaboratorRepository) {

    fun storeCommits(repoId: Long, commitInfos: List<CommitInfo>): Flow<Unit> {
        return flowOf(transaction {
            commitInfos.forEach { commitInfo ->
                if (CommitTable.select(where = CommitTable.sha.eq(commitInfo.sha)).firstOrNull() == null) {
                    val collaborator = collaboratorRepository.getOrCreateCollaborator(
                        name = commitInfo.committedBy, repoId = repoId
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
        })
    }

    fun getCommits(repoName: String, from: LocalDateTime): Flow<List<Commit>> {
        return flowOf(transaction {

            (CommitTable.join(
                otherTable = CollaboratorTable,
                joinType = JoinType.INNER,
                onColumn = CommitTable.committedBy,
                otherColumn = CollaboratorTable.id
            )).join(
                otherTable = RepoTable,
                joinType = JoinType.INNER,
                onColumn = CommitTable.repoId,
                otherColumn = RepoTable.id
            ).select(
                where = ((
                        CommitTable.repoId.eq(RepoTable.id)) and
                        CommitTable.committedBy.eq(CollaboratorTable.id) and
                        RepoTable.name.eq(repoName) and
                        CommitTable.committedTime.greaterEq(from))
            ).map {
                toCommit(it)
            }
        })
    }

    fun getCommitCount(repoName: String, from: LocalDateTime): Flow<Long> {
        return flowOf(transaction {
            (CommitTable.join(
                otherTable = CollaboratorTable,
                joinType = JoinType.INNER,
                onColumn = CommitTable.committedBy,
                otherColumn = CollaboratorTable.id
            )).join(
                otherTable = RepoTable,
                joinType = JoinType.INNER,
                onColumn = CommitTable.repoId,
                otherColumn = RepoTable.id
            ).select(
                where = ((CommitTable.repoId.eq(RepoTable.id)) and
                        CommitTable.committedBy.eq(CollaboratorTable.id) and
                        RepoTable.name.eq(repoName) and
                        CommitTable.committedTime.greaterEq(from))
            ).count()
        })
    }

    private fun toCommit(row: ResultRow): Commit {
        return Commit(
            id = row[CommitTable.id],
            sha = row[CommitTable.sha],
            message = row[CommitTable.message],
            collaborator = collaboratorRepository.toCollaborator(row)
        )
    }
}
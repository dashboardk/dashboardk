package com.dashboardk.backend.repositories

import com.dashboardk.backend.db.CollaboratorTable
import com.dashboardk.backend.domain.collaborator.Collaborator
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction

class CollaboratorRepository {

    fun getOrCreateCollaborator(name: String, repoId: Long): Collaborator {
        return transaction {
            CollaboratorTable.select(
                where = CollaboratorTable.name.eq(name).and(CollaboratorTable.repoId.eq(repoId))
            ).firstOrNull()?.let {
                toCollaborator(it)
            } ?: CollaboratorTable.insert {
                it[CollaboratorTable.name] = name
                it[CollaboratorTable.repoId] = repoId
            }.let {
                toCollaborator(it)
            }
        }
    }

    private fun toCollaborator(row: ResultRow): Collaborator {
        return Collaborator(
            id = row[CollaboratorTable.id],
            name = row[CollaboratorTable.name]
        )
    }

    private fun toCollaborator(row: InsertStatement<Number>): Collaborator {
        return Collaborator(
            id = row[CollaboratorTable.id],
            name = row[CollaboratorTable.name]
        )
    }
}
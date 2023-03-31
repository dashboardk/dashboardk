package com.dashboardk.backend.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object RepoTable : Table(name = "repo") {
    val id: Column<Long> = long("id").autoIncrement()
    val name: Column<String> = varchar("name", 100).uniqueIndex()

    override val primaryKey = PrimaryKey(id, name = "PK_Repo_ID")
}

object CommitTable : Table(name = "commit") {
    val id: Column<Long> = long("id").autoIncrement()
    val sha: Column<String> = varchar("sha", 1000)
    val message: Column<String> = varchar("message", 1000)
    val repoId: Column<Long> = long("repo_id").references(RepoTable.id)
    val committedBy: Column<Long> = long("committed_by").references(CollaboratorTable.id)
    val committedTime: Column<LocalDateTime> = datetime("committed_time")

    override val primaryKey = PrimaryKey(id, name = "PK_Commit_ID")

    init {
        uniqueIndex(customIndexName = "UK_Commit_SHA", columns = arrayOf(repoId, sha))
    }
}

object PullRequestTable : Table(name = "pull_request") {
    val id: Column<Long> = long("id").autoIncrement()
    val repoId: Column<Long> = long("repo_id").references(RepoTable.id)
    val refId: Column<String> = varchar("reference_id", 1000)
    val title: Column<String> = varchar("title", 1000)
    val state: Column<String> = varchar("state", 50)
    val createdBy: Column<Long> = long("created_by").references(CollaboratorTable.id)

    override val primaryKey = PrimaryKey(id, name = "PK_PR_ID")

    init {
        uniqueIndex(
            customIndexName = "UK_PR_REF_ID",
            columns = arrayOf(repoId, refId)
        )
    }
}

object CollaboratorTable : Table(name = "collaborator") {
    val id: Column<Long> = long("id").autoIncrement()
    val name: Column<String> = varchar("name", 100)
    val repoId: Column<Long> = long("repo_id").references(RepoTable.id)

    override val primaryKey = PrimaryKey(CollaboratorTable.id, name = "PK_COLLAB_ID")

    init {
        uniqueIndex(
            customIndexName = "UK_REPO_COLLAB_NAME",
            columns = arrayOf(repoId, name)
        )
    }
}
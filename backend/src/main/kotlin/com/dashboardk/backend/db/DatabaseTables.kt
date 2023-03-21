package com.dashboardk.backend.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object WidgetTable : Table(name = "widget") {
    val id: Column<Long> = long("id").autoIncrement()
    val type: Column<String> = varchar("widget_type", 100)
    val data: Column<String> = varchar("data", 1000)

    override val primaryKey = PrimaryKey(id, name = "PK_Widget_ID")
}

object DashboardTable : Table(name = "dashboard") {
    val id: Column<Long> = long("id").autoIncrement()
    val widgetIds: Column<String> = varchar("widget_ids", 1000)

    override val primaryKey = PrimaryKey(id, name = "PK_Widget_ID")
}

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
    val committedTime: Column<LocalDateTime> = datetime("committed_time")

    override val primaryKey = PrimaryKey(id, name = "PK_Commit_ID")

    init {
        uniqueIndex(customIndexName = "UK_Commit_SHA", columns = arrayOf(repoId, sha))
    }
}
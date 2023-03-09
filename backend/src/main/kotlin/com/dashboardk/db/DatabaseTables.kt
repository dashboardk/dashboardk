package com.dashboardk.db

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object WidgetTable : Table(name = "widget") {
    val id: Column<Long> = long("id").autoIncrement()
    val type: Column<String> = varchar("widget_type", 100)
    val data: Column<String> = varchar("data", 1000)

    override val primaryKey = PrimaryKey(id, name = "PK_Widget_ID")
}

object DashboardTable : Table(name = "dashboard") {
    val id: Column<Long> = long("id").autoIncrement()
    val widgetIds: Column<String> = varchar("widget_ids", 1000)

    override val primaryKey = PrimaryKey(DashboardTable.id, name = "PK_Widget_ID")
}

object CodeRepoTable : Table(name = "code_repo") {
    val id: Column<Long> = long("id").autoIncrement()
    val name: Column<String> = varchar("name", 100)
}

object CommitTable : Table(name = "commit") {
    val id: Column<Long> = long("id").autoIncrement()
    val sha: Column<String> = varchar("sha", 1000)
    val message: Column<String> = varchar("message", 1000)
}
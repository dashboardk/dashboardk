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
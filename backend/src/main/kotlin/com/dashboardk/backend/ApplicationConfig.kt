package com.dashboardk.backend

object ApplicationConfig {

    val DB_HOST = environment("DB_HOST", "127.0.0.1")
    val DB_PORT = environment("DB_PORT", "5432")
    val DB_USER_NAME = environment("DB_USERNAME", "dashboardk")
    val DB_PASSWORD = environment("DB_PASSWORD", "password")
    val DB_NAME = environment("DB_NAME", "dashboardk")
    val CONFIG_PATH = environment("CONFIG_PATH", this.javaClass.classLoader.getResource("dashboard.yml")?.path ?: "")

    private fun environment(name: String, default: String): String {
        return System.getenv(name) ?: default
    }
}

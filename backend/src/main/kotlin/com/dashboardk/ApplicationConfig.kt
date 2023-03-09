package com.dashboardk

object ApplicationConfig {

    val DB_HOST = environment("DB_HOST", "localhost")
    val DB_PORT = environment("DB_PORT", "5432")
    val DB_USER_NAME = environment("DB_USERNAME", "")
    val DB_PASSWORD = environment("DB_PASSWORD", "")
    val DB_NAME = environment("DB_NAME", "")
    val AUTH_SECRET = environment("AUTH_SECRET_KEY", "")

    private fun environment(name: String, default: String): String {
        return System.getenv(name) ?: default
    }
}

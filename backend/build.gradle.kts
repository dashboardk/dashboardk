plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

application {
    mainClass.set("com.dashboardk.backend.ApplicationKt")
}

ktor {
    docker {
        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
        localImageName.set("dashboardk")
        imageTag.set("0.0.1-preview")
        portMappings.set(
            listOf(
                io.ktor.plugin.features.DockerPortMapping(
                    80,
                    8080,
                    io.ktor.plugin.features.DockerPortMappingProtocol.TCP
                )
            )
        )
        externalRegistry.set(
            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
                appName = provider { "dashboardk" },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )
    }
}

java {
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    val ktorVersion = "2.2.4"
    val kotlinVersion = "1.8.10"
    val logbackVersion = "1.2.11"
    val exposedVersion = "0.41.1"
    val graphqlVersion = "6.4.0"

    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-openapi:$ktorVersion")
    implementation("io.ktor:ktor-server-swagger:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation(group = "org.jetbrains.exposed", name = "exposed-java-time", version = exposedVersion)
    implementation("io.insert-koin:koin-ktor:3.3.1")
    implementation(group = "org.postgresql", name = "postgresql", version = "42.2.27")
    implementation(group = "com.charleskorn.kaml", name = "kaml", version = "0.52.0")
    implementation(group = "com.expediagroup", name = "graphql-kotlin", version = "2.0.0.RC1")
    implementation(group = "com.expediagroup", name = "graphql-kotlin-schema-generator", version = "2.0.0.RC1")
    implementation(group = "com.zaxxer", name = "HikariCP", version = "3.4.2")
    implementation(group = "org.flywaydb", name = "flyway-core", version = "7.1.1")

    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-client-logging-jvm:2.2.4")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation(group = "com.google.truth", name = "truth", version = "1.1.3")
    implementation(group = "io.ktor", name = "ktor-client-content-negotiation", version = ktorVersion)
    implementation(group = "io.ktor", name = "ktor-serialization-kotlinx-json", version = ktorVersion)
    implementation(group = "ch.qos.logback", name = "logback-classic", "1.4.5")
    implementation(group = "io.ktor", name = "ktor-client-apache", version = ktorVersion)
}

tasks.create<Delete>("cleanStatic") {
    delete("src/main/resources/client")
}

tasks.create<Copy>("deployStatic") {
    dependsOn("cleanStatic")
    from(fileTree("../client/build"))
    into("src/main/resources/client")
}

tasks.getByName("build").dependsOn("deployStatic")

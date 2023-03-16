group = "com.dashboardk"

allprojects {
    repositories {
        mavenCentral()
    }
}


tasks.create<Delete>("clean") {
    delete("build")
}

tasks.create("buildBackend").dependsOn("backend:build")
tasks.create("buildClient") {
    dependsOn("client:build")
    finalizedBy("buildBackend")
}
tasks.create("build").dependsOn("buildClient", "buildBackend")
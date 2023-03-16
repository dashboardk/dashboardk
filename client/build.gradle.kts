plugins {
    id("com.github.node-gradle.node") version "3.5.1"
}

version = "1.0.0"

repositories {
    mavenCentral()
}

tasks.create<com.github.gradle.node.task.NodeTask>("build") {
    dependsOn("yarn")
    script.set(File("$projectDir/node_modules/react-scripts/bin/react-scripts.js"))
    args.add("build")
}

tasks.create<com.github.gradle.node.task.NodeTask>("run") {
    dependsOn("yarn")
    script.set(File("$projectDir/node_modules/react-scripts/bin/react-scripts.js"))
    args.add("run")
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}

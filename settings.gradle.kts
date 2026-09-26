pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()

        // Todo: SNAPSHOT - remove once creek-kafka cuts a 0.5.0 release.
        maven {
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
        }
    }
}

rootProject.name = "wip-state-stores-demo"

include(
    "handle-scoreboard-service",
    "api",
    "handle-occurrence-service",
    "services",
    "system-tests"
)

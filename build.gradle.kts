plugins {
    id("tools.hyperdrive.subspace.root")
}

group = "tools.hyperdrive.subspace"

val Gradle.buildSetup: IncludedBuild
    get() = includedBuild("build-setup")

val Gradle.dependencies: IncludedBuild
    get() = includedBuild("dependencies")

val Gradle.pluginApi: IncludedBuild
    get() = includedBuild("plugin-api")

val Gradle.subprojects: List<IncludedBuild>
    get() = listOf(
        buildSetup,
        dependencies,
        pluginApi,
    )

val build by tasks.creating {
    group = "build"

    gradle.subprojects.forEach { subproject ->
        dependsOn(subproject.task(":build"))
    }
}

val publishDryRun by tasks.creating {
    group = "publishing"

    gradle.subprojects.forEach { subproject ->
        dependsOn(
            subproject.task(":publishToSonatype"),
            subproject.task(":closeSonatypeStagingRepository"),
        )
    }
}

val publish by tasks.creating {
    group = "publishing"

    gradle.subprojects.forEach { subproject ->
        dependsOn(
            subproject.task(":publishToSonatype"),
            subproject.task(":closeAndReleaseSonatypeStagingRepository"),
        )
    }
}

val publishToMavenLocal by tasks.creating {
    group = "publishing"

    gradle.subprojects.forEach { subproject ->
        dependsOn(subproject.task(":publishToMavenLocal"))
    }
}

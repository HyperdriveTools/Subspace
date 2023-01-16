group = "tools.hyperdrive.subspace"

val Gradle.buildSetup: IncludedBuild
    get() = includedBuild("build-setup")

val Gradle.dependencies: IncludedBuild
    get() = includedBuild("dependencies")

val build by tasks.creating {
    group = "build"

    dependsOn(
        gradle.buildSetup.task(":build"),
        gradle.dependencies.task(":build"),
    )
}

val publish by tasks.creating {
    group = "publishing"

    gradle.buildSetup.let { buildSetup ->
        dependsOn(
            buildSetup.task(":publishToSonatype"),
            buildSetup.task(":closeSonatypeStagingRepository"),
        )
    }

    gradle.dependencies.let { dependencies ->
        dependsOn(
            dependencies.task(":publishToSonatype"),
            dependencies.task(":closeSonatypeStagingRepository"),
        )
    }
}

val publishToMavenLocal by tasks.creating {
    group = "publishing"

    dependsOn(
        gradle.buildSetup.task(":publishToMavenLocal"),
        gradle.dependencies.task(":publishToMavenLocal"),
    )
}

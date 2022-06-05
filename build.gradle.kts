group = "org.brightify.hyperdrive.subspace"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val publish by tasks.creating {
    group = "publishing"

    dependsOn(gradle.includedBuild("build-setup").task(":publish"))
    dependsOn(gradle.includedBuild("dependencies").task(":publish"))
}

val publishToMavenLocal by tasks.creating {
    group = "publishing"

    dependsOn(gradle.includedBuild("build-setup").task(":publishToMavenLocal"))
    dependsOn(gradle.includedBuild("dependencies").task(":publishToMavenLocal"))
}

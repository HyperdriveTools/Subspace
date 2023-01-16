plugins {
    id("tools.hyperdrive.subspace.root")
    id("tools.hyperdrive.subspace.plugin.kotlin")
}

group = "tools.hyperdrive.subspace"
version = libs.versions.subspace.get()

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Hyperdrive - Subspace - Kotlin Plugin API")
            description.set("API for Hyperdrive Kotlin plugins")
            scm {
                connection.set("scm:git:git://github.com/HyperdriveTools/Subspace.git")
                developerConnection.set("scm:git:git@github.com:HyperdriveTools/Subspace.git")
                url.set("https://github.com/HyperdriveTools/Subspace")
            }
        }
    }
}

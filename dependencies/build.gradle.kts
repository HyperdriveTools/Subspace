plugins {
    `version-catalog`
    `maven-publish`
    signing
    alias(libs.plugins.nexuspublish)
}

group = "tools.hyperdrive.subspace"
version = System.getenv("RELEASE_VERSION") ?: "1.0-SNAPSHOT"

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Hyperdrive - Subspace - Dependencies")
            description.set("Dependencies for Hyperdrive tools")
            url.set("https://hyperdrive.tools/")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/licenses/mit-license.php")
                }
            }
            developers {
                developer {
                    id.set("TadeasKriz")
                    name.set("Tadeas Kriz")
                    email.set("me@tadeaskriz.com")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/HyperdriveTools/Subspace.git")
                developerConnection.set("scm:git:git@github.com:HyperdriveTools/Subspace.git")
                url.set("https://github.com/HyperdriveTools/Subspace")
            }
        }
    }
}

signing {
    setRequired({
        gradle.taskGraph.hasTask(":publishToSonatype")
    })

    val signingKeyId: String? by project
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)

    sign(publishing.publications)
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

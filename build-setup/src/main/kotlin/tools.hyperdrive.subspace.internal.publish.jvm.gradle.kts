plugins {
    `maven-publish`
    signing
    kotlin("jvm")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        pom {
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

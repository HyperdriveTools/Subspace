plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    signing
    alias(libs.plugins.nexuspublish)
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin.api)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    compileOnly(gradleApi())
    compileOnly(localGroovy())
}

group = "tools.hyperdrive.subspace"
version = System.getenv("RELEASE_VERSION") ?: "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    withJavadocJar()
    withSourcesJar()
}

gradlePlugin {
    plugins {
        create("build-setup") {
            id = "tools.hyperdrive.build-setup"
            implementationClass = "org.brightify.hyperdrive.subspace.BuildSetupPlugin"
            displayName = "Hyperdrive - Subspace - Build Setup Plugin"
            description = "Hyperdrive Build Setup Plugin"
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Hyperdrive - Subspace - Build Setup Plugin")
            description.set("Convention plugins for configuring Hyperdrive tools")
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

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    signing
    alias(libs.plugins.nexuspublish)
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.kotlin.gradle.plugin.api)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.nexuspublish.gradle.plugin)

    implementation(libs.intellij.gradle.plugin) {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation(libs.dokka.gradle.plugin) {
        exclude(group = "org.jetbrains.kotlin")
    }

    compileOnly(gradleApi())
    compileOnly(localGroovy())
}

group = "tools.hyperdrive.subspace"
version = libs.versions.subspace.get()

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

    plugins {
        named("tools.hyperdrive.subspace.internal.publish.jvm") {
            displayName = "Hyperdrive - Subspace - Publish JVM Plugin (Internal)"
            description = "Convention plugin for publishing Kotlin JVM artifacts"
        }

        named("tools.hyperdrive.subspace.library.multiplatform") {
            displayName = "Hyperdrive - Subspace - Kotlin Multiplatform Library Plugin"
            description = "Convention plugin for Kotlin Multiplatform libraries"
        }

        named("tools.hyperdrive.subspace.plugin.kotlin") {
            displayName = "Hyperdrive - Subspace - Kotlin Compiler Plugin"
            description = "Convention plugin for Kotlin Compiler plugins"
        }

        named("tools.hyperdrive.subspace.root") {
            displayName = "Hyperdrive - Subspace - Root Plugin"
            description = "Convention plugin for Hypedrive root projects"
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

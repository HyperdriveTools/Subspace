plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
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

group = "org.brightify.hyperdrive.subspace"
version = "1.0-SNAPSHOT"

gradlePlugin {
    plugins {
        create("build-setup") {
            id = "build-setup"
            implementationClass = "org.brightify.hyperdrive.subspace.BuildSetupPlugin"
            displayName = "Hyperdrive - Subspace - Build Setup Plugin"
            description = "Hyperdrive Build Setup Plugin"
        }
    }
}

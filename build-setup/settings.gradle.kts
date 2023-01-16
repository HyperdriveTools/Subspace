includeBuild("../dependencies")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("libs") {
            from(files("../dependencies/libs.versions.toml"))
            version("subspace", System.getenv("RELEASE_VERSION") ?: "1.0-SNAPSHOT")
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
            version("subspace", System.getenv("RELEASE_VERSION") ?: "1.0-SNAPSHOT")
        }
    }
}

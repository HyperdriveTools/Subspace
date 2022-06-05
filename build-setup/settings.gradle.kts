includeBuild("../dependencies")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../dependencies/libs.versions.toml"))
        }
    }
}


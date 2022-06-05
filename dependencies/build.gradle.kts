plugins {
    `version-catalog`
    `maven-publish`
}

group = "org.brightify.hyperdrive.subspace"
version = "1.0-SNAPSHOT"

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

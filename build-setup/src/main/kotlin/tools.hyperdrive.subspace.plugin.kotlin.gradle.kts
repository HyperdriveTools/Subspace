import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    kotlin("jvm")
    id("tools.hyperdrive.subspace.internal.publish.jvm")
}

val libs = the<LibrariesForLibs>()

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("stdlib-jdk8"))
    compileOnly(kotlin("compiler-embeddable"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation(kotlin("compiler-embeddable"))
    testImplementation(libs.junit.jupiter)
}

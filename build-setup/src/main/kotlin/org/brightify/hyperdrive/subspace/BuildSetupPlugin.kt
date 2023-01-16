package org.brightify.hyperdrive.subspace

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create

class BuildSetupPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<SubspaceExtension>("greeting")
        project.apply(plugin = "org.jetbrains.kotlin.multiplatform")
    }
}

abstract class SubspaceExtension {
    abstract val mode: Property<Mode>

    sealed class Mode {
        data class Multiplatform(val isAppleSiliconEnabled: Boolean) : Mode()

        object JVM : Mode()
    }
}
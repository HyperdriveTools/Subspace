@file:Suppress("UNUSED")

package org.brightify.hyperdrive.subspace

import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("UNUSED_VARIABLE")
fun KotlinMultiplatformExtension.configurePlatforms(appleSilicon: Boolean = false) {
    jvm()
    ios()
    tvos()
    macosX64()

    if (appleSilicon) {
        iosSimulatorArm64()
        tvosSimulatorArm64()
        macosArm64()
    }
    js(IR) {
        browser {
            testTask {
                enabled = false
            }
        }
        nodejs()
    }

    sourceSets.apply {
        val commonMain by getting
        val commonTest by getting
        val darwinMain by creating {
            dependsOn(commonMain)
        }
        val darwinTest by creating {
            dependsOn(commonTest)
        }

        val iosMain by getting {
            dependsOn(darwinMain)
        }
        val iosTest by getting {
            dependsOn(darwinTest)
        }

        val tvosMain by getting {
            dependsOn(darwinMain)
        }
        val tvosTest by getting {
            dependsOn(darwinTest)
        }

        val macosMain by creating {
            dependsOn(darwinMain)
        }
        val macosTest by creating {
            dependsOn(darwinTest)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }
        val macosX64Test by getting {
            dependsOn(macosTest)
        }

        if (appleSilicon) {
            val iosSimulatorArm64Main by getting {
                dependsOn(iosMain)
            }
            val iosSimulatorArm64Test by getting {
                dependsOn(iosTest)
            }
            val tvosSimulatorArm64Main by getting {
                dependsOn(tvosMain)
            }
            val tvosSimulatorArm64Test by getting {
                dependsOn(tvosTest)
            }
            val macosArm64Main by getting {
                dependsOn(macosMain)
            }
            val macosArm64Test by getting {
                dependsOn(macosTest)
            }
        }
    }
}
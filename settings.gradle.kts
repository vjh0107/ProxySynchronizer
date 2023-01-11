rootProject.name = "ProxySynchronizer"

pluginManagement {
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    val barcodeGradlePluginsVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
        id("com.google.devtools.ksp") version kspVersion apply false
        id("com.github.johnrengelman.shadow") version "7.1.2" apply false
        id("com.vjh0107.ksp-extension") version "1.0.2" apply false
        id("com.vjh0107.bukkit-resource-generator") version barcodeGradlePluginsVersion apply false
    }

    repositories {
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}

fun includeAll(modulesDir: String) {
    file("${rootProject.projectDir.path}/${modulesDir.replace(":", "/")}/").listFiles()?.forEach { modulePath ->
        include("${modulesDir.replace("/", ":")}:${modulePath.name}")
    }
}

includeBuild("build-logic")
includeAll("modules")
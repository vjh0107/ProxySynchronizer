plugins {
    id("barcode.shared")
    id("com.vjh0107.ksp-extension")
    id("com.vjh0107.bukkit-resource-generator")
    id("com.github.johnrengelman.shadow")
    kotlin("plugin.serialization")
}

apply(file(project.projectDir.path + "/exclude.gradle.kts"))

val excludeSet = org.gradle.internal.Cast.uncheckedNonnullCast<List<Pair<String, String>>>(extra["excludeSet"]!!)
for ((group, module) in excludeSet) {
    configurations.runtimeClasspath.get().exclude(group, module)
}

barcodeTasks {
    bukkitResource {
        main = "com.vjh0107.proxysynchronizer.ProxySynchronizerPlugin"
        name = "ProxySynchronizer"
        author = "vjh0107"
        apiVersion = "1.19"
        depend = listOf("BarcodeFramework")
    }
}

tasks.shadowJar {
    archiveBaseName.set("ProxySynchronizer")
    archiveClassifier.set("")
    destinationDirectory.set(file("../../build_outputs"))
}

dependencies {
    api(project(":modules:core"))
    api(project(":modules:inventory"))
    api(project(":modules:properties"))
    api(project(":modules:enderchest"))

    compileOnly(Deps.Minecraft.PAPER_API)
    compileOnly(Deps.Barcode.FRAMEWORK)
    ksp(Deps.Koin.KSP_COMPILER)

    testImplementation(Deps.Barcode.FRAMEWORK)
    testImplementationAll(Deps.KOTEST)
}

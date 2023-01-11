plugins {
    id("barcode.shared")
    id("com.vjh0107.ksp-extension")
    id("com.vjh0107.bukkit-resource-generator")
    kotlin("plugin.serialization")
}

barcodeTasks {
    bukkitResource {
        main = "com.vjh0107.proxysynchronizer.ProxySynchronizerPlugin"
        name = "ProxySynchronizer"
        author = "vjh0107"
        depend = listOf("BarcodeFramework")
    }
}

dependencies {
    compileOnly(Deps.Minecraft.PAPER_API)
    compileOnly(Deps.Barcode.FRAMEWORK)
    ksp(Deps.Koin.KSP_COMPILER)

    testImplementation(Deps.Barcode.FRAMEWORK)
    testImplementationAll(Deps.KOTEST)
}

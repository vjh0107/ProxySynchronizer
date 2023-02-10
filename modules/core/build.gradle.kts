plugins {
    id("barcode.shared")
    id("com.vjh0107.ksp-extension")
    kotlin("plugin.serialization")
}

dependencies {
    compileOnly(Deps.Minecraft.PAPER_API)
    compileOnly(Deps.Barcode.FRAMEWORK)
    ksp(Deps.Koin.KSP_COMPILER)

    testImplementation(Deps.Barcode.FRAMEWORK)
    testImplementationAll(Deps.KOTEST)
}
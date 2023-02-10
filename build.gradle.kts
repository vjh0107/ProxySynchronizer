plugins {
    id("barcode.project-manager")
}

group = "com.vjh0107.proxysynchronizer"
version = "1.0.0"

subprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://jitpack.io")
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}
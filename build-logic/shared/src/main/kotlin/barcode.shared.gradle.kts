plugins {
    java
    kotlin("jvm")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.jar {
    this.archiveBaseName.set(project.rootProject.name)
}
plugins {
    java
    kotlin("jvm")
}

group = project.rootProject.group
version = project.rootProject.version

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.jar {
    this.archiveBaseName.set(project.rootProject.name)
}
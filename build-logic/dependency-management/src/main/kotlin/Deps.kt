import com.vjh0107.barcode.ProjectManagerPlugin
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate

object Deps {
    val project = ProjectManagerPlugin.project
    fun getVersion(id: String): String {
        return ProjectManagerPlugin.project.extra.properties[id].toString()
    }
    object Barcode {
        val FRAMEWORK = "com.vjh0107.barcode:barcodeframework-platform-bukkit:${getVersion("barcodeFrameworkVersion")}"
    }

    object Minecraft {
        private const val version = "1.19.2-R0.1-SNAPSHOT"

        const val PAPER_API = "io.papermc.paper:paper-api:$version"
        const val SPIGOT = "org.spigotmc:spigot:$version"
        const val SPIGOT_API = "org.spigotmc:spigot-api:$version"
        const val SPIGOT_REMAPPED = "org.spigotmc:spigot:$version:remapped-mojang"

        const val AUTH_LIB = "com.mojang:authlib:1.5.21"
        const val DATA_FIXER = "com.mojang:datafixerupper:4.0.26"
        const val BRIGADIER = "com.mojang:brigadier:1.0.18"

        const val MOCK_BUKKIT = "com.github.seeseemelk:MockBukkit-v1.19:2.144.5"

        object KyoriAdventure {
            private val kyoriAdventureVersion: String by project
            val API = "net.kyori:adventure-api:$kyoriAdventureVersion"
            private val kyoriAdventureBukkitVersion: String by project
            val BUKKIT = "net.kyori:adventure-platform-bukkit:$kyoriAdventureBukkitVersion"
        }

        object Plugin {
            const val COMMAND_API = "dev.jorel:commandapi-shade:8.5.1"
            const val VAULT = "com.github.MilkBowl:VaultAPI:1.7"
            const val PAPI = "me.clip:placeholderapi:2.11.1"
            const val MYTHICMOBS = "io.lumine:Mythic-Dist:5.1.2-SNAPSHOT"
        }
    }

    val kotlinVersion = getVersion("kotlinVersion")

    object Library {
        val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${getVersion("kotlinVersion")}"
        val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test-junit:${getVersion("kotlinVersion")}"

        const val NETTY = "io.netty:netty-all:4.1.78.Final"

        object Logger {
            const val LOGBACK_CLASSIC ="ch.qos.logback:logback-classic:1.4.0"
            const val SLF4J_JDK14 = "org.slf4j:slf4j-jdk14:2.0.1"
        }
    }

    object KOTEST : DependencySet<String> {
        private const val VERSION = "5.3.1"
        private const val KOTLIN_TEST_RUNNER = "io.kotest:kotest-runner-junit5:$VERSION"
        private const val KOTLIN_TEST_ASSERTIONS_CORE = "io.kotest:kotest-assertions-core:$VERSION"

        override fun getDependencies(): Collection<String> {
            return setOf(KOTLIN_TEST_RUNNER, KOTLIN_TEST_ASSERTIONS_CORE)
        }
    }

    object Koin {
        private val koinVersion: String by project
        private val koinAnnotationVersion: String by project
        val CORE = "io.insert-koin:koin-core:$koinVersion"
        val KTOR = "io.insert-koin:koin-ktor:$koinVersion"
        val ANNOTATIONS = "io.insert-koin:koin-annotations:$koinAnnotationVersion"
        val KSP_COMPILER = "io.insert-koin:koin-ksp-compiler:$koinAnnotationVersion"

        val TEST = "io.insert-koin:koin-test:$koinVersion"
    }
}
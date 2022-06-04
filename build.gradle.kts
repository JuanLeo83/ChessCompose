import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

group = "com.jgpl"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                // Dependency injection
                implementation("io.insert-koin:koin-core:3.2.0")

                // Database
                val kodeinDBVersion = "0.8.1-beta"
                implementation("org.kodein.db:kodein-db-jvm:$kodeinDBVersion")
                implementation("org.kodein.db:kodein-db-serializer-kotlinx:$kodeinDBVersion")
                implementation("org.kodein.db:kodein-leveldb-jni-jvm-windows:$kodeinDBVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Chess"
            packageVersion = "1.0.0"
        }
    }
}

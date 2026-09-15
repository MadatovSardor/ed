pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

// Modullar Java 21 toolchain so'raydi. Mashinada JDK 21 bo'lmasa, Gradle uni
// foojay (Adoptium va boshqalar) orqali o'zi yuklab oladi — shusiz build yiqiladi.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "ed"

include(
    ":app",
    ":modules:shared",
    ":modules:catalog",
    ":modules:cart",
    ":modules:order",
    ":modules:payment",
    ":modules:inventory",
    ":modules:notification",
    ":modules:identity",
)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

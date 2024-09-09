plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

kotlin {
    jvmToolchain(19)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    api(libs.kotlin.plugin)
    api(libs.android.gradle.plugin)
    api(libs.ksp.plugin)
    api(libs.compose.plugin)
}

gradlePlugin {
    plugins {
        register("kotlinLibraryPlugin") {
            id = "com.sliderzxc.xbike.plugins.kotlin.library"
            implementationClass = "KotlinLibraryConventionsPlugin"
        }

        register("composeLibraryPlugin") {
            id = "com.sliderzxc.xbike.plugins.compose.library"
            implementationClass = "ComposeLibraryConventionsPlugin"
        }

        register("androidLibraryPlugin") {
            id = "com.sliderzxc.xbike.plugins.android.library"
            implementationClass = "AndroidLibraryConventionsPlugin"
        }
    }
}
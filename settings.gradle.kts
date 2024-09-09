enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "XBIKE"

includeBuild("build-conventions")

include(":app")

include(":style-system")
include(":navigation")

include(
    "sdk:firebase"
)

include(
    ":foundation:mvi"
)

include(
    ":feature:splash:presentation",
)
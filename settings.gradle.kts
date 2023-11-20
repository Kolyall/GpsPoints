pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GpsPoints"
include(":app")
include(":module_domain")
include(":module_data")
include(":module_remote")
include(":core:presentation")
include(":module_db")

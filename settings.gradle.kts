pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "IT Courses Application"
include(":app")
include(":core:domain")
include(":core:data")
include(":feature:login")
include(":feature:main")
include(":feature:favourites")
include(":feature:account")
include(":feature:components")
include(":core:presentation")
include(":feature:course_detail")
include(":core:di")

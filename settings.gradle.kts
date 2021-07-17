dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Simple project"
include(
    ":app",
    ":app-demo",
    ":app-test",

    ":app-state",

    ":core-data",
    ":core-ui",
    ":core-mediator",
    ":core-unit-test",

    ":base-billing",
    ":base-otp",

    ":feature-auth",
    ":feature-main",
    ":feature-onboarding",
    ":feature-purchase",
    ":feature-otp",
)

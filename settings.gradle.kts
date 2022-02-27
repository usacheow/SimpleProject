@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SimpleProject"
include(
    ":app",
    ":app-demo",
    ":app-test",

    ":core",
    ":core-data",
    ":core-ui",
    ":core-navigation",
    ":core-unit-test",

    ":base-billing",
    ":base-otp",

    ":feature-bottom-bar",
    ":feature-auth",
    ":feature-main",
    ":feature-onboarding",
    ":feature-purchase",
    ":feature-otp",
)
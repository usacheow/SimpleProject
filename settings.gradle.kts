dependencyResolutionManagement {
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

    ":core",
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
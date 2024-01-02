plugins {
    id("com.android.application")
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.showcaseapp"

    defaultConfig {
        versionName = "8.8.8"
        versionCode = 888
        applicationId = "com.usacheow.simpleapp.uikit"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        all {
            addManifestPlaceholders(mapOf(
                "app_name" to "SA UiKit",
            ))
        }
    }
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(libs.splashscreen)
}
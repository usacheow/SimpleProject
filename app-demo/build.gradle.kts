plugins {
    id(Libs.plugin.application)
}

common()
compose()
dagger()
lifecycle()

android {
    defaultConfig {
        versionName = "8.8.8"
        versionCode = 888
        applicationId = "com.usacheow.simpleapp.uikit"
    }

    buildTypes {
        all {
            addManifestPlaceholders(mapOf(
                "app_name" to "Bank131 UiKit",
            ))
        }
    }
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(*Libs.bundle.splashscreen)
}
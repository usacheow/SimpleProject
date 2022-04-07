plugins {
    id(Libs.plugin.application)
}

android {
    defaultConfig {
        versionName = "8.8.8"
        versionCode = 888
        applicationId = "com.usacheow.simpleapp.uikit"
    }
}

common()
compose()
navigation()
dagger()
lifecycle()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(projects.featureAuth)
    implementation(projects.featureOnboarding)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.paging)
    implementation(*Libs.bundle.biometric)
}
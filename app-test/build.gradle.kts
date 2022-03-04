plugins {
    id(Libs.plugin.application)
}

android {
    defaultConfig {
        versionName = "9.9.9"
        versionCode = 999
        applicationId = "com.usacheow.simpleapp.sandbox"
    }
}

common()
navigation()
dagger()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(projects.coreData)

    implementation(*Libs.bundle.biometric)
    implementation(*Libs.bundle.splashscreen)
}
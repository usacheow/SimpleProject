plugins {
    id("app-config")
    id("navigation-config")
    id("dagger-config")
}

android {
    defaultConfig {
        versionName = "9.9.9"
        versionCode = 999
        applicationId = "com.usacheow.simpleapp.sandbox"
    }
}

dependencies {
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)
    implementation(projects.coreData)

    implementation(*Libs.bundle.biometric)
    implementation(*Libs.bundle.splashscreen)
}
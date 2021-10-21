plugins {
    id("app-config")
    id("navigation-config")
    id("lifecycle-config")
    id("dagger-config")
}

android {
    defaultConfig {
        versionName = "8.8.8"
        versionCode = 888
        applicationId = "com.usacheow.simpleapp.uikit"
    }
}

dependencies {
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreMediator)

    implementation(projects.featureAuth)
    implementation(projects.featureOnboarding)
    implementation(projects.featureOtp)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.paging)
    implementation(*Libs.bundle.biometric)
}
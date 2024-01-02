plugins {
    id("com.android.library")
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.coreui"
}

dependencies {
    api(projects.coreUiCompose)
    implementation(projects.coreCommon)

    implementation(libs.splashscreen)
    implementation(libs.bundles.navigation)
    implementation(libs.biometric)
}
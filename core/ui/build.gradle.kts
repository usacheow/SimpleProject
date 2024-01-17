plugins {
    id("com.android.library")
}

common()
compose()
di()
lifecycle()

android {
    namespace = "com.usacheow.coreui"
}

dependencies {
    api(projects.coreUiCompose)
    implementation(projects.coreCommon)

    implementation(libs.splashscreen)
    implementation(libs.bundles.navigation)
}
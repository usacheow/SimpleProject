plugins {
    id("com.android.library")
}

common("com.usacheow.coreui")
compose()
di()

dependencies {
    api(projects.coreUiKit)
    implementation(projects.coreCommon)

    implementation(libs.splashscreen)
    implementation(libs.bundles.navigation)
}
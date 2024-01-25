plugins {
    id("com.android.library")
}

common("com.usacheow.coreuicompose")
compose()
di()

dependencies {
    api(projects.coreUiTheme)
    api(libs.bundles.composeTheme)
    api(libs.bundles.composeAndroid)
    api(libs.bundles.coil)

    implementation(projects.coreCommon)
    implementation(libs.window)
}
plugins {
    id("com.android.library")
}

common()
compose()
di()

android {
    namespace = "com.usacheow.coreuicompose"
}

dependencies {
    api(projects.coreUiTheme)
    api(libs.bundles.composeTheme)
    api(libs.bundles.composeAndroid)
    api(libs.bundles.coil)

    implementation(projects.coreCommon)
    implementation(libs.window)
}
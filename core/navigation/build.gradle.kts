plugins {
    id("com.android.library")
}

common("com.usacheow.corenavigation")
compose()

dependencies {
    api(libs.bundles.navigation)
    api(libs.bundles.kotlinSerialization)

    implementation(projects.coreCommon)

    implementation(libs.browser)
}
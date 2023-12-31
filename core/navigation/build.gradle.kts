plugins {
    id("com.android.library")
}

common()
compose()

android {
    namespace = "com.usacheow.corenavigation"
}

dependencies {
    api(libs.bundles.navigation)
    api(libs.bundles.kotlinSerialization)

    implementation(projects.coreCommon)

    implementation(libs.browser)
}
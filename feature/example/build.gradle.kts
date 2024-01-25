plugins {
    id("com.android.library")
}

common()
compose()
di()

android {
    namespace = "com.usacheow.featureexample"
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(libs.bundles.firebase)
}
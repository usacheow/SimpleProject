plugins {
    id("com.android.library")
}

common()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.showcasebilling"
}

dependencies {
    implementation(libs.billing)

    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)

    implementation(libs.bundles.firebase)
}
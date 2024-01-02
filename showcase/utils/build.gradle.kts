plugins {
    id("com.android.library")
}

common()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.showcaseutils"
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)

    implementation(projects.baseSources)

    implementation(libs.androidxPreference)
    implementation(libs.bundles.firebase)
    implementation(libs.appUpdater)
    implementation(libs.biometric)
}
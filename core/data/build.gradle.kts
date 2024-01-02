plugins {
    id("com.android.library")
}

common()
lifecycle()
dagger()
room()

android {
    namespace = "com.usacheow.coredata"
}

dependencies {
    api(libs.bundles.requests)
    api(libs.bundles.kotlinSerialization)

    implementation(projects.coreCommon)

    implementation(libs.bundles.firebase)
    implementation(libs.androidxDatastorePreference)
    implementation(libs.androidxPreference)
    implementation(libs.androidxSecurity)
    implementation(libs.biometric)
}
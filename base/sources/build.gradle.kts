plugins {
    id("com.android.library")
}

common()
di()

android {
    namespace = "com.usacheow.basesources"
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)

    implementation(libs.androidxPreference)
    implementation(libs.bundles.firebase)
}
plugins {
    id("com.android.library")
}

common("com.usacheow.basesources")
di()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)

    implementation(libs.androidxPreference)
    implementation(libs.bundles.firebase)
}
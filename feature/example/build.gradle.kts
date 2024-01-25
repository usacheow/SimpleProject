plugins {
    id("com.android.library")
}

common("com.usacheow.featureexample")
compose()
di()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(libs.bundles.firebase)
}
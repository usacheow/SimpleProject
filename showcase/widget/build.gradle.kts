plugins {
    id("com.android.library")
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.featurewidget"
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreNavigation)

    implementation(libs.composeGlance)
    implementation(libs.bundles.firebase)
}
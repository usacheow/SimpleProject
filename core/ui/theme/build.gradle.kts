plugins {
    id("com.android.library")
}

common("com.usacheow.coreuitheme")
compose()
di()

dependencies {
    implementation(projects.coreCommon)

    implementation(libs.splashscreen)
    implementation(libs.bundles.composeTheme)
}
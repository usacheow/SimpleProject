plugins {
    id("com.android.library")
}

common()
compose()
di()

android {
    namespace = "com.usacheow.coreuitheme"
}

dependencies {
    implementation(projects.coreCommon)

    implementation(libs.splashscreen)
    implementation(libs.bundles.composeTheme)
}
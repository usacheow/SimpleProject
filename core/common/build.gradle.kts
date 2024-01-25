plugins {
    id("com.android.library")
}

common("com.usacheow.corecommon")
di()

dependencies {
    implementation(libs.bundles.kotlinSerialization)
    implementation(libs.bundles.firebase)
}
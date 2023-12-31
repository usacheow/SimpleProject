plugins {
    id("com.android.library")
}

common()
dagger()

android {
    namespace = "com.usacheow.corecommon"
}

dependencies {
    implementation(libs.bundles.kotlinSerialization)
    implementation(libs.bundles.firebase)
}
plugins {
    id("com.android.library")
    id("app.cash.sqldelight") version libs.versions.sqlDelightVersion
}

common()
lifecycle()
di()
database()

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
}

sqldelight {
    databases {
        create("SimpleAppDatabase") {
            packageName.set("com.usacheow.coredata")
        }
    }
}
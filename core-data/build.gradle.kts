plugins {
    id("library-config")
    id("dagger-config")
    id("room-config")
}

android {
    defaultConfig {
        buildConfigField("String", "ENDPOINT", "\"https://stub.com/api/v1/\"")
    }
}

dependencies {
    api(projects.core)
    api(*Libs.bundle.requests)
    api(*Libs.bundle.gson)

    implementation(*Libs.bundle.firebase)
    implementation(*Libs.bundle.datastore)
    implementation(*Libs.bundle.preference)
}
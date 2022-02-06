plugins {
    id("library-config")
    id("dagger-config")
    id("room-config")
}

dependencies {
    api(projects.core)
    api(*Libs.bundle.requests)
    api(*Libs.bundle.kotlinSerialization)

    implementation(*Libs.bundle.firebase)
    implementation(*Libs.bundle.datastore)
    implementation(*Libs.bundle.preference)
    implementation(*Libs.bundle.cryptoPreference)
    implementation(*Libs.bundle.biometric)
}
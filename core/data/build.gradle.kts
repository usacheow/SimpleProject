plugins {
    id(Libs.plugin.library)
}

common()
lifecycle()
dagger()
room()

dependencies {
    api(*Libs.bundle.requests)
    api(*Libs.bundle.kotlinSerialization)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.firebase)
    implementation(*Libs.bundle.datastore)
    implementation(*Libs.bundle.preference)
    implementation(*Libs.bundle.cryptoPreference)
    implementation(*Libs.bundle.biometric)
}
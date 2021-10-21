plugins {
    id("library-config")
    id("lifecycle-config")
    id("dagger-config")
}

dependencies {
    api(*Libs.bundle.billing)

    implementation(projects.coreData)
    implementation(projects.coreUi)

    implementation(*Libs.bundle.firebase)
}
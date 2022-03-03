plugins {
    id("library-config")
    id("dagger-config")
}

dependencies {
    api(*Libs.bundle.billing)

    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)

    implementation(*Libs.bundle.firebase)
    lifecycle()
}
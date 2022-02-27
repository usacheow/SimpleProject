plugins {
    id("library-config")
    id("navigation-config")
    id("lifecycle-config")
    id("dagger-config")
}

dependencies {
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(projects.baseBilling)

    implementation(*Libs.bundle.firebase)
}
plugins {
    id("library-config")
    id("lifecycle-config")
    id("dagger-config")
}

dependencies {
    implementation(projects.coreUi)
}
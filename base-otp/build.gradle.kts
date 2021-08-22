plugins {
    id("library-config")
    id("lifecycle-config")
    id("dagger-config")
}

dependencies {
    implementation(project(Modules.CORE_UI.path))
}
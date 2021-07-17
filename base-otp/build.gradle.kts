plugins {
    id("library-config")
    id("dagger-config")
}

dependencies {
    implementation(project(Modules.CORE_UI.path))
}
plugins {
    id("library-config")
    id("navigation-config")
    id("dagger-config")
    id("lifecycle-config")
}

dependencies {
    implementation(project(Modules.CORE_DATA.path))
    implementation(project(Modules.CORE_UI.path))
}
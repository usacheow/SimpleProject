plugins {
    id("library-config")
    id("navigation-config")
    id("lifecycle-config")
    id("dagger-config")
    id("test-config")
}

dependencies {
    implementation(project(Modules.CORE_DATA.path))
    implementation(project(Modules.CORE_UI.path))
    implementation(project(Modules.CORE_MEDIATOR.path))

    implementation(*Dependencies.Firebase.bundle)
}
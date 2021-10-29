plugins {
    id("library-config")
    id("navigation-config")
    id("lifecycle-config")
    id("glide-config")
    id("dagger-config")
}

dependencies {
    api(projects.core)
    api(*Libs.bundle.uiCore)
    api(*Libs.bundle.uiView)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.biometric)
    implementation(*Libs.bundle.browser)
    implementation(*Libs.bundle.firebase)
}
plugins {
    id("library-config")
    id("lifecycle-config")
    id("glide-config")
    id("dagger-config")
}

dependencies {
    api(projects.core)
    api(*Libs.bundle.uiCore)
    api(*Libs.bundle.uiView)
    api(*Libs.bundle.uiCompose)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.biometric)
}
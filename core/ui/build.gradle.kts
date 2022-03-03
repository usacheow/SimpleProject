plugins {
    id("library-config")
    id("dagger-config")
}

dependencies {
    api(projects.coreUiCompose)
    api(projects.coreUiView)

    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    lifecycle()
    glide()
}
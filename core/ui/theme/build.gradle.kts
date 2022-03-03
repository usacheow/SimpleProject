plugins {
    id("library-config")
    id("dagger-config")
}

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.viewTheme)
    implementation(*Libs.bundle.composeTheme)
}
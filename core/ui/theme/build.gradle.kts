plugins {
    id(Libs.plugin.library)
}

common()
compose()
dagger()

android {
    namespace = "com.usacheow.coreuitheme"
}

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.composeTheme)
}
plugins {
    id("app-config")
    id("navigation-config")
    id("dagger-config")
}

android {
    defaultConfig {
        versionName = "9.9.9"
        versionCode = 999
        applicationId = "com.usacheow.simpleapp.sandbox"
    }
}

dependencies {
    implementation(projects.coreUi)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.camerax)
}
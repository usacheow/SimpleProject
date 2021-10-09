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
    implementation(project(Modules.CORE_UI.path))

    implementation(*Dependencies.Android.CameraX.bundle)
}
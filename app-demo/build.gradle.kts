plugins {
    id("app-config")
    id("navigation-config")
    id("lifecycle-config")
    id("dagger-config")
}

android {
    defaultConfig {
        versionName = "8.8.8"
        versionCode = 888
        applicationId = "com.usacheow.simpleapp.uikit"
    }
}

dependencies {
    implementation(project(Modules.CORE_DATA.path))
    implementation(project(Modules.CORE_UI.path))
    implementation(project(Modules.CORE_MEDIATOR.path))

    implementation(project(Modules.FEATURE_AUTH.path))
    implementation(project(Modules.FEATURE_ON_BOARDING.path))
    implementation(project(Modules.FEATURE_OTP.path))

    implementation(*Dependencies.Android.Biometric.bundle)
}
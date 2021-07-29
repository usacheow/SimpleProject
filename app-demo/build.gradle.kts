plugins {
    id("app-config")
    id("navigation-config")
    id("dagger-config")
    id("lifecycle-config")
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

    implementation(project(Modules.APP_STATE.path))

    implementation(project(Modules.FEATURE_AUTH.path))
    implementation(project(Modules.FEATURE_ON_BOARDING.path))
    implementation(project(Modules.FEATURE_OTP.path))

    implementation(*Dependencies.Android.Biometric.impl)
}
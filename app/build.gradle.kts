plugins {
    id("app-config")
    id("navigation-config")
    id("dagger-config")
    id("lifecycle-config")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    defaultConfig {
        versionName = App.name
        versionCode = App.code
        applicationId = App.packageName
    }

    signingConfigs {
        create(BuildTypes.release) {
            storeFile = rootProject.file(KeystoreParams.path)
            storePassword = KeystoreParams.storePassword
            keyAlias = KeystoreParams.keyAlias
            keyPassword = KeystoreParams.keyPassword
        }
    }

    buildTypes {
        getByName(BuildTypes.release) {
            versionNameSuffix = BuildTypes.releaseSuffix

            signingConfig = signingConfigs.getByName(BuildTypes.release)
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName(BuildTypes.debug) {
            versionNameSuffix = BuildTypes.debugSuffix

            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(Modules.CORE_DATA.path))
    implementation(project(Modules.CORE_UI.path))
    implementation(project(Modules.CORE_MEDIATOR.path))
    implementation(project(Modules.APP_STATE.path))

    implementation(project(Modules.BASE_BILLING.path))

    implementation(project(Modules.FEATURE_MAIN.path))
    implementation(project(Modules.FEATURE_AUTH.path))
    implementation(project(Modules.FEATURE_ON_BOARDING.path))
    implementation(project(Modules.FEATURE_PURCHASE.path))
    implementation(project(Modules.FEATURE_OTP.path))

    implementation(*Dependencies.Firebase.impl)
}
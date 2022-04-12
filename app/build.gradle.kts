plugins {
    id(Libs.plugin.application)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.spotify.ruler")
}

android {
    defaultConfig {
        versionName = App.name
        versionCode = App.code
        applicationId = App.packageName
    }

    ruler {
        abi.set("arm64-v8a")
        locale.set("en")
        screenDensity.set(480)
        sdkVersion.set(27)
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
        applicationVariants.all {
            outputs.withType<com.android.build.gradle.api.ApkVariantOutput> {
                outputFileName = "${App.name}-$outputFileName"
            }
        }
        getByName(BuildTypes.release) {
            versionNameSuffix = BuildTypes.releaseSuffix

            signingConfig = signingConfigs.getByName(BuildTypes.release)
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName(BuildTypes.debug) {
            versionNameSuffix = BuildTypes.debugSuffix

            isMinifyEnabled = false
        }
    }
}

common()
compose()
dagger()
lifecycle()

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(projects.featureBottomBar)
    implementation(projects.featureMain)
    implementation(projects.featureOnboarding)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.appUpdater)
    implementation(*Libs.bundle.firebase)
    implementation(*Libs.bundle.biometric)
}
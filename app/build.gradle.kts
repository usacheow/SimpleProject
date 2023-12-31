plugins {
    id(Libs.plugin.application)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

common()
compose()
dagger()
lifecycle()

android {
    namespace = "com.usacheow.app"

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
        applicationVariants.all {
            outputs.withType<com.android.build.gradle.api.ApkVariantOutput> {
                outputFileName = "${App.name}-$outputFileName"
            }
        }
        getByName(BuildTypes.debug) {
            versionNameSuffix = BuildTypes.debugSuffix
            applicationIdSuffix = BuildTypes.debugPackageSuffix

            addManifestPlaceholders(mapOf(
                "app_name" to "SA Debug",
            ))

            isMinifyEnabled = false
        }
        getByName(BuildTypes.alpha) {
            initWith(getByName(BuildTypes.debug))
            versionNameSuffix = BuildTypes.alphaSuffix
            applicationIdSuffix = BuildTypes.alphaPackageSuffix

            addManifestPlaceholders(mapOf(
                "app_name" to "SA Alpha",
            ))

            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName(BuildTypes.release) {
            versionNameSuffix = BuildTypes.releaseSuffix
            applicationIdSuffix = BuildTypes.releasePackageSuffix

            addManifestPlaceholders(mapOf(
                "app_name" to "Sample App",
            ))

            signingConfig = signingConfigs.getByName(BuildTypes.release)
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.coreCommon)
    implementation(projects.coreData)
    implementation(projects.coreUi)
    implementation(projects.coreNavigation)

    implementation(projects.baseSources)

    implementation(projects.featureExample)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.firebase)
}
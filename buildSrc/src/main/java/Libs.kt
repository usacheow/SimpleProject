object Libs {

    private const val gmsVersion = "4.3.4"
    private const val gradleVersion = "7.3.1"
    private const val desugarVersion = "1.1.5"
    private const val kotlinVersion = "1.8.10"
    private const val kotlinSerializationVersion = "1.3.0"
    private const val coroutinesVersion = "1.6.1"
    private const val kotlinterVersion = "3.8.0"

    const val composeCompilerVersion = "1.4.3"
    private const val composeVersion = "1.4.0-beta02"
    private const val composeMaterialVersion = "1.1.0-rc01"
    private const val composeGlanceVersion = "1.0.0-alpha03"
    private const val splashVersion = "1.0.0-beta01"
    private const val activityVersion = "1.4.0"
    private const val materialVersion = "1.5.0"
    private const val pagingVersion = "3.1.0"
    private const val accompanistVersion = "0.29.1-alpha"
    private const val coilVersion = "2.1.0"

    private const val lifecycleVersion = "2.4.0"
    private const val navigationVersion = "1.0.0-rc05"
    private const val appUpdaterVersion = "1.8.1"
    private const val billingVersion = "4.0.0"
    private const val browserVersion = "1.4.0"
    private const val biometricVersion = "1.2.0-alpha04"
    private const val windowVersion = "1.0.0"

    private const val diVersion = "7.19.0"

    private const val crashlyticsGradleVersion = "2.5.2"
    private const val crashlyticsVersion = "17.4.0"
    private const val analyticsVersion = "18.0.2"
    private const val messagingVersion = "21.0.1"
    private const val configVersion = "20.0.4"

    private const val datastoreVersion = "1.0.0-rc01"
    private const val securityVersion = "1.1.0-alpha03"
    private const val preferenceVersion = "1.1.1"
    private const val roomVersion = "2.4.0"
    private const val ktorVersion = "2.3.0"

    private const val mockitoVersion = "2.7.22"
    private const val truthVersion = "0.34"
    private const val junitVersion = "4.12"
    private const val espressoVersion = "3.1.0"
    private const val runnerVersion = "1.1.0"

    object plugin {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val kotlin_kapt = "kotlin-kapt"
        const val kotlin_parcelize = "kotlin-parcelize"
        const val kotlin_serialization = "kotlinx-serialization"
        const val kotlin_android = "kotlin-android"
        const val lint = "org.jmailen.kotlinter"
    }

    object classpath {
        const val google_services = "com.google.gms:google-services:$gmsVersion"
        const val android_gradle = "com.android.tools.build:gradle:$gradleVersion"
        const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
        const val crashlytics_gradle = "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsGradleVersion"
        const val kotlinter = "org.jmailen.gradle:kotlinter-gradle:$kotlinterVersion"
    }

    object bundle {
        val kotlin = arrayOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        val coroutines = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")
        val kotlinSerialization = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion",
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

        val desugar = arrayOf("com.android.tools:desugar_jdk_libs:$desugarVersion")

        val di = arrayOf(
            "org.kodein.di:kodein-di:$diVersion",
            "org.kodein.di:kodein-di-framework-compose:$diVersion")

        val firebase = arrayOf(
            "com.google.firebase:firebase-crashlytics-ktx:$crashlyticsVersion",
            "com.google.firebase:firebase-analytics-ktx:$analyticsVersion",
            "com.google.firebase:firebase-messaging-ktx:$messagingVersion",
            "com.google.firebase:firebase-config-ktx:$configVersion")

        val composeCompiler =arrayOf("androidx.compose.compiler:compiler:$composeCompilerVersion")
        val composeRuntime =arrayOf("androidx.compose.runtime:runtime:$composeVersion")
        val composeTheme =arrayOf(
            "androidx.compose.ui:ui:$composeVersion",
            "androidx.compose.ui:ui-tooling:$composeVersion",
            "androidx.compose.ui:ui-tooling-preview:$composeVersion",
            "androidx.compose.material3:material3:$composeMaterialVersion",
            "androidx.compose.material3:material3-window-size-class:$composeMaterialVersion",
            "androidx.compose.material:material-icons-extended:$composeVersion",
            "com.google.android.material:material:$materialVersion")
        val composeWidgetTheme =arrayOf("androidx.glance:glance-appwidget:$composeGlanceVersion")
        val composeKit =arrayOf(
            "androidx.activity:activity-compose:$activityVersion",
            "androidx.compose.foundation:foundation:$composeVersion",
            "androidx.compose.foundation:foundation-layout:$composeVersion",
            "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion",
            "com.google.accompanist:accompanist-pager:$accompanistVersion",
            "com.google.accompanist:accompanist-placeholder-material:$accompanistVersion",
            "com.google.accompanist:accompanist-flowlayout:$accompanistVersion",
            "androidx.compose.animation:animation:$composeVersion",
            "io.coil-kt:coil-compose:$coilVersion",
            "io.coil-kt:coil-svg:$coilVersion")

        val paging = arrayOf("androidx.paging:paging-runtime:$pagingVersion")
        val splashscreen = arrayOf("androidx.core:core-splashscreen:$splashVersion")

        val lifecycle = arrayOf(
            "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-process:$lifecycleVersion")
        const val lifecycleKapt = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"

        val navigation = arrayOf(
            "cafe.adriel.voyager:voyager-navigator:$navigationVersion",
            "cafe.adriel.voyager:voyager-bottom-sheet-navigator:$navigationVersion",
            "cafe.adriel.voyager:voyager-tab-navigator:$navigationVersion",
            "cafe.adriel.voyager:voyager-transitions:$navigationVersion",
            "cafe.adriel.voyager:voyager-androidx:$navigationVersion",
            "cafe.adriel.voyager:voyager-kodein:$navigationVersion")

        val appUpdater = arrayOf("com.google.android.play:core-ktx:$appUpdaterVersion")
        val billing = arrayOf("com.android.billingclient:billing-ktx:$billingVersion")
        val browser = arrayOf("androidx.browser:browser:$browserVersion")
        val biometric = arrayOf("androidx.biometric:biometric-ktx:$biometricVersion")
        val window = arrayOf("androidx.window:window:$windowVersion")

        val datastore = arrayOf("androidx.datastore:datastore-preferences:$datastoreVersion")
        val cryptoPreference = arrayOf("androidx.security:security-crypto:$securityVersion")
        val preference = arrayOf("androidx.preference:preference-ktx:$preferenceVersion")

        val room = arrayOf(
            "androidx.room:room-runtime:$roomVersion",
            "androidx.room:room-ktx:$roomVersion")
        const val roomKapt = "androidx.room:room-compiler:$roomVersion"

        val requests = arrayOf(
            "io.ktor:ktor-client-core:$ktorVersion",
            "io.ktor:ktor-client-android:$ktorVersion",
            "io.ktor:ktor-client-content-negotiation:$ktorVersion",
            "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion",
            "io.ktor:ktor-client-logging:$ktorVersion",
            "io.ktor:ktor-client-auth:$ktorVersion")

        const val unitTestsRunner = "android.support.test.runner.AndroidJUnitRunner"
        val unitTests = arrayOf(
            "junit:junit:$junitVersion",
            "org.mockito:mockito-core:$mockitoVersion",
            "com.google.truth:truth:$truthVersion")
        val uiTests = arrayOf(
            "androidx.test.espresso:espresso-core:$espressoVersion",
            "androidx.test.espresso:espresso-intents:$espressoVersion",
            "androidx.test:runner:$runnerVersion",
            "org.mockito:mockito-core:$mockitoVersion",
            "com.google.truth:truth:$truthVersion")
    }
}
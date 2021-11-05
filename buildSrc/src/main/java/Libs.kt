object Libs {

    private const val gmsVersion = "4.3.4"
    private const val gradleVersion = "7.0.3"
    private const val desugarVersion = "1.1.5"
    private const val kotlinVersion = "1.5.31"
    private const val kotlinSerializationVersion = "1.3.0"
    private const val coroutinesVersion = "1.5.1"

    private const val androidxVersion = "1.3.1"
    private const val androidxCoreVersion = "1.7.0-beta02"
    private const val splashVersion = "1.0.0-alpha02"
    private const val activityVersion = "1.4.0"
    private const val fragmentVersion = "1.4.0-rc01"
    private const val materialVersion = "1.5.0-alpha05"
    private const val cardViewVersion = "1.0.0"
    private const val viewPagerVersion = "1.0.0"
    private const val recyclerVersion = "1.3.0-alpha01"
    private const val constraintVersion = "2.0.4"
    private const val pagingVersion = "3.0.0"
    private const val roundedImageVersion = "2.3.0"
    private const val decoroVersion = "1.5.0"
    private const val shimmerVersion = "0.5.0"
    private const val pageIndicatorVersion = "1.2.1"

    private const val lifecycleVersion = "2.4.0"
    private const val navigationVersion = "2.4.0-beta02"
    private const val appUpdaterVersion = "1.8.1"
    private const val billingVersion = "4.0.0"
    private const val browserVersion = "1.0.0"
    private const val biometricVersion = "1.2.0-alpha03"
    private const val cameraXVersion = "1.0.0"
    private const val cameraXViewVersion = "1.0.0-alpha26"

    private const val hiltVersion = "2.37"
    private const val hiltJetpackVersion = "1.0.0"

    private const val crashlyticsGradleVersion = "2.5.2"
    private const val crashlyticsVersion = "17.4.0"
    private const val analyticsVersion = "18.0.2"
    private const val messagingVersion = "21.0.1"
    private const val configVersion = "20.0.4"

    private const val glideVersion = "4.12.0"
    private const val datastoreVersion = "1.0.0-rc01"
    private const val preferenceVersion = "1.1.1"
    private const val roomVersion = "2.3.0"
    private const val gsonVersion = "2.8.6"
    private const val retrofitVersion = "2.9.0"
    private const val okHttpVersion = "4.9.0"
    private const val chuckVersion = "1.1.0"

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
        const val hilt = "dagger.hilt.android.plugin"
        const val navigation = "androidx.navigation.safeargs.kotlin"
    }

    object classpath {
        const val google_services = "com.google.gms:google-services:$gmsVersion"
        const val android_gradle = "com.android.tools.build:gradle:$gradleVersion"
        const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
        const val crashlytics_gradle = "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsGradleVersion"
        const val hilt_gradle = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val navigation_safeArgs_gradle = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object bundle {
        val kotlin = arrayOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        val coroutines = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        val kotlinSerialization = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion",
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

        val desugar = arrayOf("com.android.tools:desugar_jdk_libs:$desugarVersion")

        val hilt = arrayOf(
            "com.google.dagger:hilt-android:$hiltVersion",
            "androidx.hilt:hilt-navigation-fragment:$hiltJetpackVersion")
        const val hiltKapt = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltKaptViewModel = "androidx.hilt:hilt-compiler:$hiltJetpackVersion"

        val firebase = arrayOf(
            "com.google.firebase:firebase-crashlytics-ktx:$crashlyticsVersion",
            "com.google.firebase:firebase-analytics-ktx:$analyticsVersion",
            "com.google.firebase:firebase-messaging-ktx:$messagingVersion",
            "com.google.firebase:firebase-config-ktx:$configVersion")

        val uiCore = arrayOf(
            "androidx.appcompat:appcompat:$androidxVersion",
            "androidx.core:core-ktx:$androidxCoreVersion")
        val uiView = arrayOf(
            "com.google.android.material:material:$materialVersion",

            "androidx.activity:activity-ktx:$activityVersion",
            "androidx.fragment:fragment-ktx:$fragmentVersion",

            "androidx.cardview:cardview:$cardViewVersion",
            "androidx.viewpager2:viewpager2:$viewPagerVersion",
            "androidx.recyclerview:recyclerview:$recyclerVersion",
            "androidx.constraintlayout:constraintlayout:$constraintVersion",

            "com.facebook.shimmer:shimmer:$shimmerVersion",
            "com.makeramen:roundedimageview:$roundedImageVersion",
            "ru.tinkoff.decoro:decoro:$decoroVersion",
            "ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:$pageIndicatorVersion")

        val splashscreen = arrayOf("androidx.core:core-splashscreen:$splashVersion")
        val paging = arrayOf("androidx.paging:paging-runtime:$pagingVersion")

        val lifecycle = arrayOf(
            "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        const val lifecycleKapt = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"

        val navigation = arrayOf(
            "androidx.navigation:navigation-fragment-ktx:$navigationVersion",
            "androidx.navigation:navigation-ui-ktx:$navigationVersion",
            "androidx.navigation:navigation-compose:$navigationVersion")

        val appUpdater = arrayOf("com.google.android.play:core-ktx:$appUpdaterVersion")
        val billing = arrayOf("com.android.billingclient:billing-ktx:$billingVersion")
        val browser = arrayOf("androidx.browser:browser:$browserVersion")
        val biometric = arrayOf("androidx.biometric:biometric-ktx:$biometricVersion")
        val camerax = arrayOf(
            "androidx.camera:camera-core:$cameraXVersion",
            "androidx.camera:camera-camera2:$cameraXVersion",
            "androidx.camera:camera-lifecycle:$cameraXVersion",
            "androidx.camera:camera-view:$cameraXViewVersion",
            "androidx.camera:camera-extensions:$cameraXViewVersion")

        val glide = arrayOf("com.github.bumptech.glide:glide:$glideVersion")
        const val glideKapt = "com.github.bumptech.glide:compiler:$glideVersion"

        val datastore = arrayOf("androidx.datastore:datastore-preferences:$datastoreVersion")
        val preference = arrayOf("androidx.preference:preference-ktx:$preferenceVersion")
        val gson = arrayOf("com.google.code.gson:gson:$gsonVersion")

        val room = arrayOf(
            "androidx.room:room-runtime:$roomVersion",
            "androidx.room:room-ktx:$roomVersion")
        const val roomKapt = "androidx.room:room-compiler:$roomVersion"

        val requests = arrayOf(
            "com.squareup.retrofit2:converter-gson:$retrofitVersion",
            "com.squareup.retrofit2:retrofit:$retrofitVersion",
            "com.squareup.okhttp3:okhttp:$okHttpVersion",
            "com.squareup.okhttp3:logging-interceptor:$okHttpVersion",
            "com.readystatesoftware.chuck:library:$chuckVersion")

        const val unitTestsRunner = "android.support.test.runner.AndroidJUnitRunner"
        val unitTests = arrayOf(
            "junit:junit:$junitVersion",
            "org.mockito:mockito-core:$mockitoVersion",
            "org.mockito:mockito-inline:$mockitoVersion",
            "org.mockito:mockito-android:$mockitoVersion",
            "com.google.truth:truth:$truthVersion")
        val uiTests = arrayOf(
            "androidx.test.espresso:espresso-core:$espressoVersion",
            "androidx.test.espresso:espresso-intents:$espressoVersion",
            "androidx.test:runner:$runnerVersion",
            "org.mockito:mockito-inline:$mockitoVersion",
            "org.mockito:mockito-android:$mockitoVersion",
            "com.google.truth:truth:$truthVersion")
    }
}
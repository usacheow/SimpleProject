object Dependencies {

    object General {

        object Gms {

            private const val version = "4.3.4"
            const val path = "com.google.gms:google-services:$version"
        }

        object Gradle {

            private const val version = "7.0.1"
            const val path = "com.android.tools.build:gradle:$version"
        }

        object Kotlin {

            const val version = "1.5.21"
            val bundle = arrayOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version")
            const val path = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

            object Coroutines {

                private const val version = "1.5.1"
                val bundle = arrayOf(
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version",
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version",
                )
            }

            object Lint {

                private const val version = "3.4.5"
                const val path = "org.jmailen.gradle:kotlinter-gradle:$version"
                const val plugin = "org.jmailen.kotlinter"
            }

            object Kapt {

                const val plugin = "kotlin-kapt"
            }

            object Parcelize {

                const val plugin = "kotlin-parcelize"
            }

            object Android {

                const val plugin = "kotlin-android"
            }
        }

        object Desugar {

            private const val version = "1.1.5"
            val bundle = arrayOf("com.android.tools:desugar_jdk_libs:$version")
        }
    }

    object Android {

        object App {

            const val plugin = "com.android.application"
        }

        object Library {

            const val plugin = "com.android.library"
        }

        object Lifecycle {

            private const val version = "2.4.0-rc01"
            val bundle = arrayOf(
                "androidx.lifecycle:lifecycle-runtime-ktx:$version",
                "androidx.lifecycle:lifecycle-viewmodel-compose:$version",
                "androidx.lifecycle:lifecycle-viewmodel-ktx:$version",
            )
            const val kapt = "androidx.lifecycle:lifecycle-compiler:$version"
        }

        object Ui {

            const val composeVersion = "1.1.0-alpha01"
            private const val androidxVersion = "1.3.1"
            private const val androidxCoreVersion = "1.7.0-beta02"
            private const val playCoreVersion = "1.8.1"
            private const val splashVersion = "1.0.0-alpha02"
            private const val insetVersion = "0.15.0"
            private const val activityVersion = "1.3.0"
            private const val fragmentVersion = "1.4.0-alpha10"
            private const val materialVersion = "1.3.0"
            private const val cardViewVersion = "1.0.0"
            private const val viewPagerVersion = "1.0.0"
            private const val recyclerVersion = "1.3.0-alpha01"
            private const val constraintVersion = "2.0.4"
            private const val pagingVersion = "3.0.0"
            private const val roundedImageVersion = "2.3.0"
            private const val decoroVersion = "1.5.0"
            private const val shimmerVersion = "0.5.0"
            private const val pageIndicatorVersion = "1.2.1"
            val bundle = arrayOf(
                "androidx.appcompat:appcompat:$androidxVersion",
                "androidx.core:core-ktx:$androidxCoreVersion",
                "com.google.android.play:core-ktx:$playCoreVersion",
                "androidx.core:core-splashscreen:$splashVersion",

                "androidx.compose.compiler:compiler:$composeVersion",
                "androidx.compose.runtime:runtime:$composeVersion",
                "androidx.compose.animation:animation:$composeVersion",
                "androidx.compose.ui:ui:$composeVersion",
                "androidx.compose.ui:ui-tooling:$composeVersion",
                "androidx.compose.ui:ui-tooling-preview:$composeVersion",
                "androidx.compose.foundation:foundation:$composeVersion",
                "androidx.compose.foundation:foundation-layout:$composeVersion",
                "androidx.compose.material:material:$composeVersion",
                "androidx.activity:activity-compose:$activityVersion",

                "com.google.accompanist:accompanist-insets:$insetVersion",

                "androidx.fragment:fragment:$fragmentVersion",
                "androidx.fragment:fragment-ktx:$fragmentVersion",

                "com.google.android.material:material:$materialVersion",
                "androidx.cardview:cardview:$cardViewVersion",
                "androidx.viewpager2:viewpager2:$viewPagerVersion",
                "androidx.recyclerview:recyclerview:$recyclerVersion",
                "androidx.constraintlayout:constraintlayout:$constraintVersion",
                "androidx.paging:paging-runtime:$pagingVersion",

                "com.makeramen:roundedimageview:$roundedImageVersion",
                "ru.tinkoff.decoro:decoro:$decoroVersion",
                "com.facebook.shimmer:shimmer:$shimmerVersion",
                "ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:$pageIndicatorVersion",
            )
        }

        object Navigation {

            private const val version = "2.4.0-alpha10"
            val bundle = arrayOf(
                "androidx.navigation:navigation-fragment-ktx:$version",
                "androidx.navigation:navigation-ui-ktx:$version",
                "androidx.navigation:navigation-compose:$version",
            )
            const val path = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            const val plugin = "androidx.navigation.safeargs.kotlin"
        }

        object Billing {

            private const val version = "4.0.0"
            val bundle = arrayOf("com.android.billingclient:billing-ktx:$version")
        }

        object Browser {

            private const val version = "1.0.0"
            val bundle = arrayOf("androidx.browser:browser:$version")
        }

        object Biometric {

            private const val version = "1.2.0-alpha03"
            val bundle = arrayOf("androidx.biometric:biometric-ktx:$version")
        }

        object CameraX {

            private const val cameraXVersion = "1.0.0"
            private const val cameraXViewVersion = "1.0.0-alpha26"
            val bundle = arrayOf(
                "androidx.camera:camera-core:$cameraXVersion",
                "androidx.camera:camera-camera2:$cameraXVersion",
                "androidx.camera:camera-lifecycle:$cameraXVersion",
                "androidx.camera:camera-view:$cameraXViewVersion",
                "androidx.camera:camera-extensions:$cameraXViewVersion",
            )
        }
    }

    object Dagger {

        private const val hiltVersion = "2.37"
        private const val hiltJetpackVersion = "1.0.0"
        val bundle = arrayOf(
            "com.google.dagger:hilt-android:$hiltVersion",
            "androidx.hilt:hilt-navigation-fragment:$hiltJetpackVersion",
        )
        const val kapt = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val kaptViewModel = "androidx.hilt:hilt-compiler:$hiltJetpackVersion"
        const val path = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val plugin = "dagger.hilt.android.plugin"
    }

    object Firebase {

        private const val crashlyticsGradleVersion = "2.5.2"
        private const val crashlyticsVersion = "17.4.0"
        private const val analyticsVersion = "18.0.2"
        private const val messagingVersion = "21.0.1"
        private const val configVersion = "20.0.4"
        val bundle = arrayOf(
            "com.google.firebase:firebase-crashlytics-ktx:$crashlyticsVersion",
            "com.google.firebase:firebase-analytics-ktx:$analyticsVersion",
            "com.google.firebase:firebase-messaging-ktx:$messagingVersion",
            "com.google.firebase:firebase-config-ktx:$configVersion",
        )
        const val path = "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsGradleVersion"
    }

    object Data {

        object Glide {

            private const val version = "4.12.0"
            val bundle = arrayOf("com.github.bumptech.glide:glide:$version")
            const val kapt = "com.github.bumptech.glide:compiler:$version"
        }

        object DataStore {

            private const val version = "1.0.0-rc01"
            val bundle = arrayOf("androidx.datastore:datastore-preferences:$version")
        }

        object Preference {

            private const val version = "1.1.1"
            val bundle = arrayOf("androidx.preference:preference-ktx:$version")
        }

        object Room {

            private const val version = "2.3.0"
            val bundle = arrayOf(
                "androidx.room:room-runtime:$version",
                "androidx.room:room-ktx:$version",
            )
            const val kapt = "androidx.room:room-compiler:$version"
        }

        object Gson {

            private const val version = "2.8.6"
            val bundle = arrayOf("com.google.code.gson:gson:$version")
        }

        object Requests {

            private const val retrofitVersion = "2.9.0"
            private const val okHttpVersion = "4.9.0"
            private const val chuckVersion = "1.1.0"
            val bundle = arrayOf(
                "com.squareup.retrofit2:converter-gson:$retrofitVersion",
                "com.squareup.retrofit2:retrofit:$retrofitVersion",
                "com.squareup.okhttp3:okhttp:$okHttpVersion",
                "com.squareup.okhttp3:logging-interceptor:$okHttpVersion",
                "com.readystatesoftware.chuck:library:$chuckVersion",
            )
        }
    }

    object Tests {

        private const val mockitoVersion = "2.7.22"
        private const val truthVersion = "0.34"

        object Unit {

            private const val junitVersion = "4.12"
            const val runner = "android.support.test.runner.AndroidJUnitRunner"
            val bundle = arrayOf(
                "junit:junit:$junitVersion",
                "org.mockito:mockito-core:$mockitoVersion",
                "org.mockito:mockito-inline:$mockitoVersion",
                "org.mockito:mockito-android:$mockitoVersion",
                "com.google.truth:truth:$truthVersion",
            )
        }

        object Ui {

            private const val espressoVersion = "3.1.0"
            private const val runnerVersion = "1.1.0"
            val bundle = arrayOf(
                "androidx.test.espresso:espresso-core:$espressoVersion",
                "androidx.test.espresso:espresso-intents:$espressoVersion",
                "androidx.test:runner:$runnerVersion",
                "org.mockito:mockito-inline:$mockitoVersion",
                "org.mockito:mockito-android:$mockitoVersion",
                "com.google.truth:truth:$truthVersion",
            )
        }
    }
}
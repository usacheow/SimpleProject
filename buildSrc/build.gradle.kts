import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {
        register("library-config") {
            id = "library-config"
            implementationClass = "plugin.LibraryConfigPlugin"
        }
        register("app-config") {
            id = "app-config"
            implementationClass = "plugin.AppConfigPlugin"
        }
        register("navigation-config") {
            id = "navigation-config"
            implementationClass = "plugin.NavigationConfigPlugin"
        }
        register("dagger-config") {
            id = "dagger-config"
            implementationClass = "plugin.DaggerConfigPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    api(kotlin("gradle-plugin:1.6.10"))
//    implementation(gradleApi())
//    implementation(localGroovy())
}
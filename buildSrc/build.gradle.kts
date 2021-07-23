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
        register("glide-config") {
            id = "glide-config"
            implementationClass = "plugin.GlideConfigPlugin"
        }
        register("dagger-config") {
            id = "dagger-config"
            implementationClass = "plugin.DaggerConfigPlugin"
        }
        register("lifecycle-config") {
            id = "lifecycle-config"
            implementationClass = "plugin.LifecycleConfigPlugin"
        }
        register("compose-config") {
            id = "compose-config"
            implementationClass = "plugin.ComposeConfigPlugin"
        }
        register("room-config") {
            id = "room-config"
            implementationClass = "plugin.RoomConfigPlugin"
        }
        register("test-config") {
            id = "test-config"
            implementationClass = "plugin.TestConfigPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.0-rc01")
}
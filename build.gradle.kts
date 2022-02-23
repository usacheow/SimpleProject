buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(Libs.classpath.android_gradle)
        classpath(Libs.classpath.kotlin_gradle)
        classpath(Libs.classpath.kotlin_serialization)
        classpath(Libs.classpath.hilt_gradle)
        classpath(Libs.classpath.google_services)
        classpath(Libs.classpath.crashlytics_gradle)
        classpath(Libs.classpath.navigation_safeArgs_gradle)
        classpath(Libs.classpath.kotlinter)
        classpath(Libs.classpath.ruler)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
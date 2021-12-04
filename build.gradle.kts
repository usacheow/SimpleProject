buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.classpath.android_gradle)
        classpath(Libs.classpath.kotlin_gradle)
        classpath(Libs.classpath.kotlin_serialization)
        classpath(Libs.classpath.hilt_gradle)
        classpath(Libs.classpath.google_services)
        classpath(Libs.classpath.crashlytics_gradle)
        classpath(Libs.classpath.navigation_safeArgs_gradle)
        classpath("com.android.tools.build:gradle:7.0.3")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
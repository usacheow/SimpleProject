buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.classpath.android_gradle)
        classpath(Libs.classpath.kotlin_gradle)
        classpath(Libs.classpath.hilt_gradle)
        classpath(Libs.classpath.google_services)
        classpath(Libs.classpath.crashlytics_gradle)
        classpath(Libs.classpath.navigation_safeArgs_gradle)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(libs.classpathAndroidGradle)
        classpath(libs.classpathGoogleServices)
        classpath(libs.classpathKotlinGradle)
        classpath(libs.classpathKotlinSerialization)
        classpath(libs.classpathCrashlyticsGradle)
        classpath(libs.classpathKotlinter)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
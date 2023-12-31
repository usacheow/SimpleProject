import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.classpathAndroidGradle)
    api(libs.classpathKotlinGradle)
    implementation("com.squareup:javapoet:1.13.0")
}
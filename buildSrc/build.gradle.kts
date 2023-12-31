import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.2.0")
    api(kotlin("gradle-plugin:1.9.21"))
    implementation("com.squareup:javapoet:1.13.0")
}
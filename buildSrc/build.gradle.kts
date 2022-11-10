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
    implementation("com.android.tools.build:gradle:7.3.1")
    api(kotlin("gradle-plugin:1.7.10"))
    implementation("com.squareup:javapoet:1.13.0")
}
buildscript {

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath (libs.gradle)
        classpath (libs.hilt.android.gradle.plugin)
        classpath (libs.kotlinx.serialization.json)
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    id("com.android.application") version "8.1.0" apply false
//    id ("com.android.library") version "7.4.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
}
buildscript {

    repositories {
        mavenCentral()
    }

    dependencies {
        val kotlinVersion = "2.45"

        classpath ("com.android.tools.build:gradle:8.1.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$kotlinVersion")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id ("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}
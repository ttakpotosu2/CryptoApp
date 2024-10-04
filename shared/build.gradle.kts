plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.androidx.compose.bom))
            implementation(libs.navigation.compose)
            implementation(libs.androidx.core.splashscreen)
            
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.androidx.activity.compose.v192)
            implementation(libs.androidx.ui)
            implementation(libs.androidx.ui.graphics)
            implementation(libs.androidx.ui.tooling.preview)
            implementation(libs.androidx.material3)
            implementation (libs.androidx.material.icons.extended)
            
            implementation (libs.ktor.client.core)
            implementation (libs.ktor.client.json)
            implementation (libs.ktor.client.android)
            implementation (libs.ktor.client.content.negotiation)
            implementation (libs.ktor.client.serialization)
            implementation (libs.ktor.client.logging)
            implementation (libs.ktor.client.cio)
            implementation (libs.logback.classic)
            implementation (libs.kotlinx.serialization.json)
            // Koin
            implementation (libs.koin.android)
            implementation (libs.koin.androidx.navigation)
            implementation (libs.koin.androidx.compose)
            //Coil
            implementation(libs.coil.compose)
            
            //Y Charts
            implementation(libs.ycharts)
            
            // Shimmer
            implementation(libs.compose.shimmer)
            
            //Splashscreen
            implementation(libs.androidx.core.splashscreen)
            // Compose dependencies
            implementation (libs.androidx.lifecycle.viewmodel.compose)
            implementation (libs.androidx.navigation.compose)
            
            
            // Coroutines
            implementation (libs.kotlinx.coroutines.core)
            implementation (libs.kotlinx.coroutines.android)
            
            // Coroutine Lifecycle Scopes
            implementation (libs.androidx.lifecycle.viewmodel.ktx)
            implementation (libs.androidx.lifecycle.runtime.ktx)
        }
        
        iosMain.dependencies {
        
        }
        
        androidMain.dependencies {
        
        }
        
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

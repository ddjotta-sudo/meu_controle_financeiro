plugins {
    id("com.android.library")
    kotlin("kapt")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.mlkit:text-recognition:16.1.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
}
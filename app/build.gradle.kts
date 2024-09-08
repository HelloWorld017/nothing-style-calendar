plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "dev.nenw.nothingcalendar"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.nenw.nothingcalendar"
        minSdk = 31
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.glance:glance-preview:1.0.0-alpha06")
    implementation("androidx.glance:glance-appwidget:1.0.0")
    implementation("androidx.glance:glance-appwidget-preview:1.0.0-alpha06")
    implementation("com.google.android.glance.tools:appwidget-host:0.2.2")
    implementation("com.google.android.material:material:1.11.0")
    implementation("me.onebone:toolbar-compose:2.3.5")

    implementation("androidx.compose.ui:ui-tooling:1.6.1")
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.6.1")
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mathgameapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mathgameapp"
        minSdk = 24
        targetSdk = 35 
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {
    // Core AndroidX Libraries
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.10.1")

    // Jetpack Compose BOM (Ensures all Compose dependencies are compatible)
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))

    // Jetpack Compose UI Dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Material 3
    implementation("androidx.compose.material3:material3:1.1.2")

    // Jetpack Compose Navigation (For Navigating Screens)
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Jetpack Compose ViewModel (For State Management)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Kotlin Coroutines (Fixes "Unresolved reference: LaunchedEffect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Debugging Tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


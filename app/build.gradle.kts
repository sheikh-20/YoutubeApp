plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.application.youtubeapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.application.youtubeapp"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    buildTypes {
        debug {
            isDebuggable = true
            val YOUTUBE_API_KEY: String by project

            buildConfigField(type = "String", name = "YOUTUBE_BASE_URL", value = "\"https://youtube.googleapis.com/\"")
            buildConfigField(type = "String", name = "YOUTUBE_API_KEY", value = YOUTUBE_API_KEY)

        }
        release {

            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val YOUTUBE_API_KEY: String by project

            buildConfigField(type = "String", name = "YOUTUBE_BASE_URL", value = "\"https://youtube.googleapis.com/\"")
            buildConfigField(type = "String", name = "YOUTUBE_API_KEY", value = YOUTUBE_API_KEY)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.21"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraint.layout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.hilt.navigation.fragment)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)


    // Timber for log
    implementation(libs.timber)

    // Splash screen
    implementation(libs.androidx.splash)

    // Material icon extended
    implementation(libs.androidx.material.icon)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    // Retrofit with Scalar Converter
    implementation(libs.scalar.converter)

    // Kotlin Serialization
    implementation(libs.kotlin.serialization)

    // Retrofit with Jakewharton Converter
    implementation(libs.jakewharton.converter )

    // Coil
    implementation(libs.coil)

    // Paging 3
    implementation(libs.androidx.paging.ktx)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)

    implementation(libs.play.services.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)

    implementation(libs.coil)
}
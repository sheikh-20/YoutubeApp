plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("plugin.serialization") version "1.9.21"
}

android {
    namespace = "com.application.youtubeapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.application.youtubeapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileSdkPreview = "UpsideDownCake"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraint.layout)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation("androidx.hilt:hilt-navigation-fragment:1.1.0")

    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.extra.get("nav_version")}")
    implementation("androidx.navigation:navigation-ui-ktx:${rootProject.extra.get("nav_version")}")


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


}
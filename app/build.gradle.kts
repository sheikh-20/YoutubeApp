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

    val timberVersion = "5.0.1"
    val paging_version = "3.2.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
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
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // Splash screen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")

    // Material icon extended
    implementation("androidx.compose.material:material-icons-extended:1.6.0-alpha02")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")

    // Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Retrofit with Jakewharton Converter
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // Coil
    implementation("io.coil-kt:coil:2.4.0")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")


    implementation("androidx.media3:media3-exoplayer:1.2.0")
    implementation("androidx.media3:media3-exoplayer-dash:1.2.0")
    implementation("androidx.media3:media3-ui:1.2.0")


}
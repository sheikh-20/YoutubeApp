// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("nav_version", "2.7.6")
    }
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.google.services)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra.get("nav_version")}")
        classpath(libs.gradle)
    }
}
plugins {
    id("com.android.application") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}
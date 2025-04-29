// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false version "1.9.25"
    id("com.google.dagger.hilt.android") version "2.52" apply false
    alias(libs.plugins.android.library) apply false
}

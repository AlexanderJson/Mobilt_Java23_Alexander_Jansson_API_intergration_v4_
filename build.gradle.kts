plugins {
    id("com.android.application") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    alias(libs.plugins.android.library) apply false
}

buildscript {
    dependencies {
        classpath (libs.hilt.android.gradle.plugin)
    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
}

buildscript {
    dependencies {
        classpath (libs.hilt.android.gradle.plugin)
    }
}
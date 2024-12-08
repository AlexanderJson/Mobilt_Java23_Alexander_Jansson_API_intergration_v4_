import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("com.google.gms.google-services")

}



android {
    namespace = "com.example.nyilnmning"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nyilnmning"
        minSdk = 25
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }



}



dependencies {
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.litert.support.api)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    implementation ("androidx.security:security-crypto:1.1.0-alpha03")
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.bumptech.glide:glide:4.12.0") {
        exclude(group = "com.google.code.findbugs", module = "jsr305")
    }

    implementation ("androidx.paging:paging-runtime:3.1.1")

    implementation(libs.androidx.appcompat) {
        exclude(group = "androidx.annotation")
    }

    implementation(libs.material) {
        exclude(group = "androidx.annotation")
    }
    implementation ("androidx.fragment:fragment-ktx:1.8.4")

    implementation ("com.google.firebase:firebase-auth:22.1.1")
    implementation ("com.google.firebase:firebase-database:20.1.0")
    implementation ("com.google.firebase:firebase-firestore:24.7.1")

    implementation(libs.androidx.constraintlayout.v214)
    implementation(libs.material)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")
}

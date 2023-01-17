plugins {
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.xinkev.ghusers.kmp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.xinkev.ghusers.kmp.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.android.core)
    implementation(libs.lifecycle.runtime)
    implementation(libs.activity.compose)
    implementation(libs.kotlin.serialization)
    implementation(libs.coil.compose)
    implementation(libs.hilt)
    implementation(libs.accompanist.systemui)
    implementation(libs.timber)
    implementation(libs.okhttp)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.androidx.paging)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    kapt(libs.hilt.compiler)

    releaseImplementation(libs.chucker.noop)
    debugImplementation(libs.chucker)

    testImplementation(libs.junit)
    androidTestImplementation(libs.android.junit)
    androidTestImplementation(libs.expresso)
    androidTestImplementation(libs.compose.ui.test.junit)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}

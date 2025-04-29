plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

configurations.all {
    exclude(group = "com.google.guava", module = "listenablefuture")
}

android {
    namespace = "com.iagomichel.wefittest.test"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
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
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        android.buildFeatures.buildConfig = true
    }
}

dependencies {
    testApi(libs.junit)

    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
    androidTestApi(platform(libs.androidx.compose.bom))
    androidTestApi(libs.androidx.ui.test.junit4)
    androidTestApi(libs.hilt.android.testing)
    testImplementation(libs.testng)
    api(libs.testng)
    api(libs.hilt.android.testing)
    api(libs.mockk)
    api(libs.androidx.junit.ktx)
    api(libs.kotlin.test.junit)
    api(libs.kotlinx.coroutines.test)
    api("androidx.arch.core:core-testing:2.2.0")
}
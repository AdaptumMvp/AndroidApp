plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.adaptum.adaptumandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.adaptum.adaptumandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.Core.material)
    implementation(Dependencies.Core.constraintLayout)

    testImplementation(Dependencies.Core.Test.junit)
    androidTestImplementation(Dependencies.Core.Test.androidJunit)
    androidTestImplementation(Dependencies.Core.Test.espresso)
}

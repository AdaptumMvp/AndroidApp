plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.adaptum.adaptumandroid"
    compileSdk = 34

    android.buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "ru.adaptum.adaptumandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppDependencies.Compose.composeСompilerVersion
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"http://79.174.80.178:8000/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        release {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL", "\"http://79.174.80.178:8000/\"")
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

    // Core
    implementation(AppDependencies.Core.coreKtx)
    implementation(AppDependencies.Core.appCompat)
    implementation(AppDependencies.Core.material)
    implementation(AppDependencies.Core.constraintLayout)
    implementation(AppDependencies.Core.fragmentKtx)

    // Lifecycle
    implementation(AppDependencies.Lifecycle.runtime)
    implementation(AppDependencies.Lifecycle.viewModel)

    // Retrofit
    implementation(AppDependencies.Retrofit.retrofit)
    implementation(AppDependencies.Retrofit.converterGson)
    implementation(AppDependencies.Retrofit.okHttp)
    implementation(AppDependencies.Retrofit.okHttpLoggingInterceptor)

    // Room
    implementation(AppDependencies.Room.room)
    implementation(AppDependencies.Compose.composeMaterial3)
    ksp(AppDependencies.Room.compiler)

    // Dagger
    implementation(AppDependencies.Dagger.dagger)
    ksp(AppDependencies.Dagger.compiler)

    // Glide
    implementation(AppDependencies.Glide.glide)
    ksp(AppDependencies.Glide.compiler)
    implementation(AppDependencies.Glide.glide_compose)

    // Compose
    implementation(AppDependencies.Compose.ui)
    implementation(AppDependencies.Compose.activityCompose)
    implementation(AppDependencies.Compose.material)
    implementation(AppDependencies.Compose.uiToolingPreview)
    implementation(AppDependencies.Compose.viewModel)
    implementation(AppDependencies.Compose.composeUiGraphics)

    androidTestImplementation(AppDependencies.Compose.junit)
    debugImplementation(AppDependencies.Compose.uiTooling)

    // Test
    testImplementation(AppDependencies.Core.Test.junit)
    androidTestImplementation(AppDependencies.Core.Test.androidJunit)
    androidTestImplementation(AppDependencies.Core.Test.espresso)
}

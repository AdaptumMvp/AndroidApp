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

    // Core
    implementation(AppDependencies.Core.coreKtx)
    implementation(AppDependencies.Core.appCompat)
    implementation(AppDependencies.Core.material)
    implementation(AppDependencies.Core.constraintLayout)

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
    ksp(AppDependencies.Room.compiler)

    // Dagger
    implementation(AppDependencies.Dagger.dagger)
    ksp(AppDependencies.Dagger.compiler)

    // Glide
    implementation(AppDependencies.Glide.glide)
    ksp(AppDependencies.Glide.compiler)

    // Test
    testImplementation(AppDependencies.Core.Test.junit)
    androidTestImplementation(AppDependencies.Core.Test.androidJunit)
    androidTestImplementation(AppDependencies.Core.Test.espresso)
}

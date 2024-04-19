@Suppress("ktlint:standard:property-naming")
object AppDependencies {
    object Core {
        private const val coreKtxVersion = "1.12.0"
        private const val appCompatVersion = "1.6.1"
        private const val materialVersion = "1.11.0"
        private const val constraintLayoutVersion = "2.1.4"
        private const val fragmentKtxVersion = "1.6.2"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"

        object Test {
            private const val junitVersion = "4.13.2"
            private const val androidJunitVersion = "1.1.5"
            private const val espressoVersion = "3.5.1"

            const val junit = "junit:junit:$junitVersion"
            const val androidJunit = "androidx.test.ext:junit:$androidJunitVersion"
            const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        }
    }

    object Lifecycle {
        private const val version = "2.7.0"

        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        private const val okHttpVersion = "4.9.3"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object Room {
        private const val version = "2.6.1"

        const val room = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Dagger {
        private const val version = "2.48.1"

        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Glide {
        private const val glideVersion = "4.15.1"
        private const val compilerVersion = "4.12.0"
        private const val glideComposeVersion = "1.0.0-beta01"

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val compiler = "com.github.bumptech.glide:compiler:$compilerVersion"
        const val glide_compose = "com.github.bumptech.glide:compose:$glideComposeVersion"
    }

    object Compose {
        const val composeСompilerVersion = "1.5.10"

        private const val composeVersion = "1.6.5"
        private const val activityComposeVersion = "1.8.2"
        private const val viewModelVersion = "2.5.1"
        private const val composeMaterial3Version = "1.2.1"
        private const val composeUiGraphicsVersion = "1.6.5"

        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
        const val junit = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelVersion"
        const val composeMaterial3 = "androidx.compose.material3:material3-android:$composeMaterial3Version"
        const val composeUiGraphics = "androidx.compose.ui:ui-graphics:$composeUiGraphicsVersion"
    }
}

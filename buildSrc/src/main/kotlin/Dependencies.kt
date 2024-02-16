@Suppress("ktlint:standard:property-naming")
object Dependencies {
    object Core {
        private const val coreKtxVersion = "1.12.0"
        private const val appCompatVersion = "1.6.1"
        private const val materialVersion = "1.11.0"
        private const val constraintLayoutVersion = "2.1.4"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        object Test {
            private const val junitVersion = "4.13.2"
            private const val androidJunitVersion = "1.1.5"
            private const val espressoVersion = "3.5.1"

            const val junit = "junit:junit:$junitVersion"
            const val androidJunit = "androidx.test.ext:junit:$androidJunitVersion"
            const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        }
    }
}

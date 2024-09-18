plugins {
    alias(libs.plugins.android.library.conventions)
}

dependencies {
    // AndroidX Credentials
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.auth)
    // Coroutines
    implementation(libs.kotlinx.coroutines)
    // Google Identity
    implementation(libs.google.identity)
    // Modules
    implementation(projects.foundation.exceptionHandling)
}
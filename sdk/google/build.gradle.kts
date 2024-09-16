plugins {
    alias(libs.plugins.android.library.conventions)
}

dependencies {
    // AndroidX Credentials
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.auth)
    // Google Identity
    implementation(libs.google.identity)
}
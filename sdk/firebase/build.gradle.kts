plugins {
    alias(libs.plugins.android.library.conventions)
    alias(libs.plugins.google.services)
}

dependencies {
    implementation(libs.firebase.auth)
}
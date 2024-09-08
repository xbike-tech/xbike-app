plugins {
    alias(libs.plugins.compose.library.conventions)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.decompose)
    implementation(libs.decompose.jetbrains.compose)
    implementation("com.arkivanov.decompose:decompose-android:3.0.0")
    implementation("com.arkivanov.decompose:extensions-compose-android:3.0.0")
}
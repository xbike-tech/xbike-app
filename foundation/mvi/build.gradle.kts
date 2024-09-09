plugins {
    alias(libs.plugins.compose.library.conventions)
}

dependencies {
    implementation(libs.decompose)
    implementation(libs.flowmvi.core)
    implementation(libs.flowmvi.compose)
}
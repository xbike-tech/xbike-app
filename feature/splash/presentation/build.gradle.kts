plugins {
    alias(libs.plugins.compose.library.conventions)
}

dependencies {
    // FlowMVI
    implementation(libs.flowmvi.android)
    implementation(libs.flowmvi.compose)
    implementation(libs.flowmvi.core)
    implementation(libs.decompose)
    //implementation(libs.decompose.jetbrains.compose)
    // Modules
    implementation(projects.foundation.mvi)
}
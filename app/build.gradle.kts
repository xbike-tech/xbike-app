import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.sliderzxc.xbike"
    compileSdk = libs.versions.compileSdk.get().toInt()

    val secretPropertiesFile = rootProject.file("secret.properties")
    val secretProperties = Properties()
    secretProperties.load(FileInputStream(secretPropertiesFile))


    defaultConfig {
        applicationId = "com.sliderzxc.xbike"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GOOGLE_AUTH_SERVER_CLIENT_ID", secretProperties.getProperty("GOOGLE_AUTH_SERVER_CLIENT_ID"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions.jvmTarget = "21"

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Jetpack Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    // AndroidX Credentials
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.auth)
    // Google Identity
    implementation(libs.google.identity)
    // Modules
    implementation(projects.styleSystem)
    implementation(projects.navigation)
    implementation(projects.sdk.google)
}
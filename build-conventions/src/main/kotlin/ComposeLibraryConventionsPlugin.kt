import com.android.build.api.dsl.CommonExtension
import com.sliderzxc.xbike.plugins.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeLibraryConventionsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            with(dependencies) {
                add("implementation", platform(libs.findLibrary("compose-bom").get()))
                add("implementation", libs.findLibrary("compose-activity").get())
                add("implementation", libs.findLibrary("compose-material3").get())
                add("implementation", libs.findLibrary("compose-ui-tooling").get())
                add("implementation", libs.findLibrary("compose-ui-tooling-preview").get())
            }

            extensions.configure(CommonExtension::class.java) {
                val compileSdkVersion = libs.findVersion("compileSdk").get().toString().toInt()
                val composeCompilerVersion = libs.findVersion("jetpackComposeCompilerVersion").get()

                compileSdk = compileSdkVersion
                namespace = "com.sliderzxc.xbike.plugins.compose"
                buildFeatures.compose = true
                composeOptions.kotlinCompilerExtensionVersion = composeCompilerVersion.toString()
            }
        }
    }
}

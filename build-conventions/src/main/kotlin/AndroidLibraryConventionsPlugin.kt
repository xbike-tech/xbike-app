import com.android.build.api.dsl.CommonExtension
import com.sliderzxc.xbike.plugins.configuration.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure(CommonExtension::class.java) {
                val compileSdkVersion = libs.findVersion("compileSdk").get().toString().toInt()

                compileSdk = compileSdkVersion
                namespace = "com.sliderzxc.xbike.plugins.android"

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
            }
        }
    }
}
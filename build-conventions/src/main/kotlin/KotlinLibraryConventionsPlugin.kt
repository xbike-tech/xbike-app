import com.android.build.api.dsl.CommonExtension
import com.sliderzxc.xbike.plugins.configuration.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class KotlinLibraryConventionsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("org.jetbrains.kotlin.jvm")

            extensions.configure<KotlinJvmProjectExtension> {
                jvmToolchain {
                    languageVersion.set(org.gradle.jvm.toolchain.JavaLanguageVersion.of(21))
                }
            }

            extensions.configure(CommonExtension::class.java) {
                val compileSdkVersion = libs.findVersion("compileSdk").get().toString().toInt()

                compileSdk = compileSdkVersion
                namespace = "com.sliderzxc.xbike.plugins.kotlin"

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
            }
        }
    }
}

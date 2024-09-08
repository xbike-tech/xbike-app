import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.gradle.kotlin.dsl.configure

class KotlinLibraryConventionsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.kotlin.jvm")

        target.extensions.configure<KotlinJvmProjectExtension> {
            jvmToolchain {
                languageVersion.set(org.gradle.jvm.toolchain.JavaLanguageVersion.of(19))
            }
        }
    }
}

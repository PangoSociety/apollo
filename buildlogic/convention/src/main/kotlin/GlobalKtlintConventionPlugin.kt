import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class GlobalKtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // TODO: Call plugin alias from libs.versions.toml
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")
            configure<KtlintExtension> {
                verbose.set(true)
                android.set(true)
                outputToConsole.set(true)
                filter {
                    exclude("**/generated/**")
                    include("**/kotlin/**")
                    exclude("**/build/**")
                }
            }

        }
    }

}
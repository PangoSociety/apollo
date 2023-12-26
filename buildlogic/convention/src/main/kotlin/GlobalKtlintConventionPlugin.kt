import org.gradle.api.*
import org.gradle.kotlin.dsl.*
import org.jlleitschuh.gradle.ktlint.*

class GlobalKtlintConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // TODO: Call plugin alias from libs.versions.toml
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            configure<KtlintExtension> {
                enableExperimentalRules.set(true)
                verbose.set(true)
                outputToConsole.set(true)
                filter {
                    exclude { element -> element.file.path.contains("build/") }
                    include("**/kotlin/**")
                }
            }
        }
    }
}
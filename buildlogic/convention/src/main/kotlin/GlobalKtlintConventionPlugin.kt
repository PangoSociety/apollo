import org.gradle.api.*
import org.gradle.kotlin.dsl.*
import org.jlleitschuh.gradle.ktlint.*

class GlobalKtlintConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // TODO: Call plugin alias from libs.versions.toml
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            configure<KtlintExtension> {
                verbose.set(true)
                outputToConsole.set(true)
                filter {
                    include("**/kotlin/**")
                    exclude { element -> element.file.path.contains("build/") }
                }
            }
        }
    }
}
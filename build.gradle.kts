plugins {
    alias(libs.plugins.kotlin.jvm) apply false // This plugin is necessary for ktlint
    alias(libs.plugins.ktlint) apply true
}

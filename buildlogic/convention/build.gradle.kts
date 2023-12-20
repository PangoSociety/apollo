import org.jetbrains.kotlin.gradle.tasks.KotlinCompile




plugins {
    `kotlin-dsl`
}

group="dev.pango.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("globalKtlint") {
            id = "apollo.global.ktlint"
            implementationClass = "GlobalKtlintConventionPlugin"
        }
    }
}
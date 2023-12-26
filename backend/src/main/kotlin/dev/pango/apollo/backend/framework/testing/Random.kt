package dev.pango.apollo.backend.framework.testing

import kotlin.random.*

fun Random.generateDecision(trueProbability: Double = 0.5): Boolean {
    require(trueProbability in 0.0..1.0) { "Probability must be in the range [0, 1]" }
    val randomValue = Random.nextDouble()
    return randomValue < trueProbability
}

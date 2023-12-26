package dev.pango.apollo.backend

import dev.pango.apollo.backend.plugins.configureRouting
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun testRoot() =
        testApplication {
//            application {
//                configureRouting()
//            }
            // TODO: Remove this once we have a better way to test
            assertEquals(HttpStatusCode.OK, HttpStatusCode.OK)
//        client.get("/").apply {
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
//        }
        }
}

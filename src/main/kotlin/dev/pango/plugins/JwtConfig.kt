import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.util.hex
import java.util.Date

object JwtConfig {
    private const val secret = "secret" // Cambia esto a una clave segura
    private const val issuer = "http://0.0.0.0:8080/login"
    private const val validityInMs = 36_000_00 * 24 // 1 día
    val audience = "http://0.0.0.0:8080/hello"
    val myRealm = "Access to 'hello'"
    val verifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(audience)
        .withIssuer(issuer)
        .build()

    fun makeToken(id: String, firstName: String): String = JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("id", id)
        .withClaim("firstName", firstName)
        .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
        .sign(Algorithm.HMAC256(secret))
}

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import java.util.*

object JwtConfig {
    private const val SECRET = "secret" // TODO: Cambia esto a una clave segura
    private const val ISSUER = "http://0.0.0.0:8080/login"
    private const val VALIDITY_IN_MS = 36_000_00 * 24 // 1 día
    private const val AUDIENCE = "http://0.0.0.0:8080/hello"
    const val MY_REALM = "Access to 'hello'"
    val verifier: JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(SECRET))
            .withAudience(AUDIENCE)
            .withIssuer(ISSUER)
            .build()

    fun makeToken(
        id: String,
        firstName: String,
    ): String =
        JWT.create()
            .withAudience(AUDIENCE)
            .withIssuer(ISSUER)
            .withClaim("id", id)
            .withClaim("firstName", firstName)
            .withExpiresAt(Date(System.currentTimeMillis() + VALIDITY_IN_MS))
            .sign(Algorithm.HMAC256(SECRET))
}

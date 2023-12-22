//import com.auth0.jwt.*
//import com.auth0.jwt.algorithms.*
//import dev.pango.apollo.backend.modules.userauth.aplication.service.generateRandomSecret
//import java.util.*
//import java.util.concurrent.TimeUnit
//
//object JwtConfig {
//    private var SECRET = generateRandomSecret(32)
////    private const val ISSUER = "http://0.0.0.0:8080/login"
//    private const val EXPIRATION_DAY = 30 // 30 dias
////    private const val AUDIENCE = "http://0.0.0.0:8080/hello"
//    const val MY_REALM = "Access to 'hello'"
//    private val expirationDate = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(EXPIRATION_DAY.toLong()))
//    val verifier: JWTVerifier =
//        JWT
//            .require(Algorithm.HMAC256(SECRET))
////            .withAudience(AUDIENCE)
////            .withIssuer(ISSUER)
//            .build()
//
//    fun makeToken(
//        id: String,
//        firstName: String,
//    ): String =
//        JWT.create()
////            .withAudience(AUDIENCE)
////            .withIssuer(ISSUER)
//            .withClaim("id", id)
//            .withClaim("firstName", firstName)
//            .withExpiresAt(expirationDate)
//            .sign(Algorithm.HMAC256(SECRET))
//}

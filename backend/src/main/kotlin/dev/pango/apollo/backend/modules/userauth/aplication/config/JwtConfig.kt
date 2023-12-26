package dev.pango.apollo.backend.modules.userauth.aplication.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import dev.pango.apollo.backend.modules.userauth.aplication.service.generateRandomSecret
import java.util.Date
import java.util.concurrent.TimeUnit

object JwtConfig {

    var SECRET = generateRandomSecret(32)
    const val EXPIRATION_DAY = 30 // 1 día
    const val MY_REALM = "DOMINIO...'"
    val expirationDate =
        Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(EXPIRATION_DAY.toLong()))
    val verifier: JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(SECRET))
//            .withAudience(AUDIENCE)
//            .withIssuer(ISSUER)
            .build()
    //    private const val ISSUER = "http://0.0.0.0:8080/login"
    //    private const val AUDIENCE = "http://0.0.0.0:8080/hello"
}
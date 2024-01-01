package dev.pango.apollo.backend.framework.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import dev.pango.apollo.backend.modules.userauth.aplication.config.*
import java.util.Date

class JwtService() {
    private fun generateToken(
        email: String,
        expireIn: Int,
    ): String {
        return JWT.create()
//            TODO add methodes for JWT
//            .withAudience(AUDIxENCE)
//            .withIssuer(ISSUER)
//            .withClaim("id", jwtData.id)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + (expireIn * 24)))
            .sign(Algorithm.HMAC256(JwtConfig.SECRET))
    }

    fun createAccessToken(email: String): String =
        generateToken(email, 3_600_000)

    fun createRefreshToken(email: String): String =
        generateToken(email, 86_400_000)

    fun verifyRefreshToken(token: String): DecodedJWT? {
        val decodedJwt: DecodedJWT? = getDecodedJwt(token)

        return decodedJwt?.let {
            decodedJwt
        }
    }

    private fun getDecodedJwt(token: String): DecodedJWT? =
        try {
            JwtConfig.verifier.verify(token)
        } catch (ex: Exception) {
            null
        }
}

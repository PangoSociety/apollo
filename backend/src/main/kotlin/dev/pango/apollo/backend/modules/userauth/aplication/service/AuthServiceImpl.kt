package dev.pango.apollo.backend.modules.userauth.aplication.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.pango.apollo.backend.modules.userauth.aplication.config.JwtConfig
import java.util.Date

class AuthServiceImpl : AuthService {

    override suspend fun generateToken(firstName: String, expireIn: Int): String {
        return JWT.create()
//            .withAudience(AUDIxENCE)
//            .withIssuer(ISSUER)
//            .withClaim("id", jwtData.id)
            .withClaim("firstName", firstName)
            .withExpiresAt(Date(System.currentTimeMillis() + (expireIn * 20)))
            .sign(Algorithm.HMAC256(JwtConfig.SECRET))
    }

    override suspend fun createAccessToken(firstName: String): String =
        generateToken(firstName, 3_600_000)

    override suspend fun createRefreshToken(firstName: String): String =
        generateToken(firstName, 86_400_000)
}
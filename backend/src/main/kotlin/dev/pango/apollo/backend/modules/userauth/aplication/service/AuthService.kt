package dev.pango.apollo.backend.modules.userauth.aplication.service

interface AuthService {
    suspend fun generateToken(firstName: String, expireIn: Int): String

    suspend fun  createAccessToken(firstName:String) :String
    suspend fun  createRefreshToken(firstName:String) :String


}
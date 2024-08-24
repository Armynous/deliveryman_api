package com.example.deliveryman.util

import com.example.deliveryman.entity.RoleEnum
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtUtil(
    @Value("\${jwt.secret-key}")
    val secretKey: String,
    @Value("\${jwt.expirationTime}")
    val expirationTime: Long
) {
    fun generateToken(username: String, roles: List<RoleEnum>): String {
        val rolesString = roles.joinToString(",") { it.name }

        return Jwts.builder()
            .setSubject(username)
            .claim("role", rolesString)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun getSigningKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractUsername(token: String): String {
        return extractAllClaim(token).subject
    }

    fun extractAllClaim(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun isTokenExpired(token: String): Boolean {
        return extractAllClaim(token).expiration.before(Date())
    }

    fun validateToken(token: String, username: String): Boolean {
        return (username == extractUsername(token) && !isTokenExpired(token))
    }

}
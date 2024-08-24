package com.example.deliveryman.user.response

data class LoginResponse(
    val token: String,
    val expirationTime: Long
)

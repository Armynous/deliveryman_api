package com.example.deliveryman.user.service

import com.example.deliveryman.user.request.LoginRequest
import com.example.deliveryman.user.request.SignupRequest
import com.example.deliveryman.user.response.LoginResponse

interface UserService {
    fun signUp(request: SignupRequest)
    fun login(request: LoginRequest): LoginResponse
}
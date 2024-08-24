package com.example.deliveryman.user.service.impl

import com.example.deliveryman.entity.RoleEnum
import com.example.deliveryman.entity.User
import com.example.deliveryman.exception.AuthenticationException
import com.example.deliveryman.exception.BadRequestException
import com.example.deliveryman.exception.NotFoundException
import com.example.deliveryman.user.repository.UserRepository
import com.example.deliveryman.user.request.LoginRequest
import com.example.deliveryman.user.request.SignupRequest
import com.example.deliveryman.user.response.LoginResponse
import com.example.deliveryman.user.service.UserService
import com.example.deliveryman.util.JwtUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtUtil: JwtUtil
): UserService {
    override fun signUp(request: SignupRequest) {
        val user = userRepository.findUserByEmail(request.email)
        if (user.isPresent) {
            throw BadRequestException("This email already exist.")
        }
        val roleEnum = RoleEnum.fromValue(request.role)
            ?: throw IllegalArgumentException("Invalid role value: ${request.role}")

        val newUser = User(
            name = request.name,
            email = request.email,
            username = request.username,
            password = passwordEncoder.encode(request.password),
            role = roleEnum
        )

        userRepository.save(newUser)
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findUserByEmail(request.email).orElseThrow{
            throw NotFoundException("Not Found User")
        }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw AuthenticationException("Invalid username or password")
        }

        val token = jwtUtil.generateToken(user.username, listOf(user.role!!))
        val expirationTime = jwtUtil.expirationTime

        return LoginResponse(
            token = token,
            expirationTime = expirationTime
        )
    }

}
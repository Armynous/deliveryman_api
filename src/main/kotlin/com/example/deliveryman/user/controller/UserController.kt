package com.example.deliveryman.user.controller

import com.example.deliveryman.user.request.LoginRequest
import com.example.deliveryman.user.request.SignupRequest
import com.example.deliveryman.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignupRequest): ResponseEntity<Any> {
        return ResponseEntity.ok().body(userService.signUp(request))
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(userService.login(request))
    }
}
package com.example.deliveryman.config

import com.example.deliveryman.exception.NotFoundException
import com.example.deliveryman.user.repository.UserRepository
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class ApplicationConfiguration(
    private val userRepository: UserRepository,
    private val  passwordEncoder: BCryptPasswordEncoder
): AuthenticationProvider {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        // Find the user by username
        val user = userRepository.findUserByEmail(username).orElseThrow{
            throw NotFoundException("Not found this user")
        }

        if (passwordEncoder.matches(password, user.password)) {
            return UsernamePasswordAuthenticationToken(username, password)
        } else {
            throw NotFoundException("Not found this user")
        }

    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}
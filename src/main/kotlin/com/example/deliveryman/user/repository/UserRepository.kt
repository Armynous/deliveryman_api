package com.example.deliveryman.user.repository

import com.example.deliveryman.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, Long> {
    @Query("select * from users where email = :email and is_deleted != true", nativeQuery = true)
    fun findUserByEmail(email: String): Optional<User>
}
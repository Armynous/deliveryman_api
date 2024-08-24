package com.example.deliveryman.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    @Enumerated(EnumType.ORDINAL)
    val role: RoleEnum?,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(name = "created_at", nullable = false)
    val createdAt: Date = Date(),
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(name = "modified_at", nullable = false)
    val modifiedAt: Date = Date(),
    @Column(name = "is_deleted", nullable = false)
    val isDeleted: Boolean? = false
): Serializable
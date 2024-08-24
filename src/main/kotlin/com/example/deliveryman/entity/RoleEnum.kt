package com.example.deliveryman.entity

enum class RoleEnum(val value: Int) {
    SUPER_ADMIN(0),
    ADMIN(1),
    USER(2);

    companion object {
        fun fromValue(value: Int): RoleEnum? {
            return entries.firstOrNull { it.value == value }
        }
    }
}

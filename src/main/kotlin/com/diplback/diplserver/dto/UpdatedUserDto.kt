package com.diplback.diplserver.dto

data class UpdatedUserDto(
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val password: String,
    val photoUser: String?
)
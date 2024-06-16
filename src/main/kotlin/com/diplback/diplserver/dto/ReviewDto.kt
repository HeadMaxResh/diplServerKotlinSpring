package com.diplback.diplserver.dto

import com.diplback.diplserver.model.User

data class ReviewDto(
    val rate: Int,
    val user: User,
    val dignityText: String,
    val flawsText: String,
    val commentText: String
)
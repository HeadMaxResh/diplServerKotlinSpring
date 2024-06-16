package com.diplback.diplserver.dto

data class MessageDto(
    val senderId: Int,
    val receiverId: Int,
    val content: String
)
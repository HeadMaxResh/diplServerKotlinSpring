package com.diplback.diplserver.model

import com.diplback.diplserver.Table.TABLE_MESSAGE
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = TABLE_MESSAGE)
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = -1,
    val senderId: Int,
    val receiverId: Int,
    val content: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
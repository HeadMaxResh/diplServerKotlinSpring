package com.diplback.diplserver.repository

import com.diplback.diplserver.model.Chat
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepo : JpaRepository<Chat, Integer> {

}
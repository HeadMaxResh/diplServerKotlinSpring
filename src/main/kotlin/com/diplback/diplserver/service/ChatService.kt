package com.diplback.diplserver.service

import com.diplback.diplserver.model.Chat

interface ChatService {

    fun findAll(): List<Chat?>?

    fun findBySenderOrReceiver(senderID: Int, receiverID: Int): List<Chat?>?

    fun findByReceiver(receiverID: Int): List<Chat?>?

    fun save(theChat: Chat?)

}
package com.diplback.diplserver.service

import com.diplback.diplserver.model.Chat
import com.diplback.diplserver.repository.ChatRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatServiceImpl : ChatService {
    @Autowired
    private val chatRepository: ChatRepo? = null
    override fun findAll(): MutableList<Chat>? {
        return chatRepository?.findAll()
    }

    override fun save(theChat: Chat?) {
        if (theChat != null) {
            chatRepository?.save(theChat)
        }
    }

    override fun findBySenderOrReceiver(senderID: Int, receiverID: Int): List<Chat> {
        val allChats: List<Chat>? = chatRepository?.findAll()
        val result: MutableList<Chat> = ArrayList<Chat>()
        if (allChats != null) {
            for (chat in allChats) {
                if ((chat.senderId == senderID.toString() || chat.receiverId == senderID.toString()) &&
                    (chat.receiverId == receiverID.toString() || chat.senderId == receiverID.toString())) {
                    result.add(chat)
                }
            }
        }
        return result
    }

    override fun findByReceiver(receiverID: Int): List<Chat> {
        val allChats: List<Chat>? = chatRepository?.findAll()
        val result: MutableList<Chat> = ArrayList<Chat>()
        if (allChats != null) {
            for (chat in allChats) {
                if (chat.senderId == receiverID.toString() || chat.receiverId == receiverID.toString()) {
                    result.add(chat)
                }
            }
        }
        return result
    }
}
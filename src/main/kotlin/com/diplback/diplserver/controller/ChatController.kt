package com.diplback.diplserver.controller

import com.diplback.diplserver.dto.MessageDto
import com.diplback.diplserver.model.Message
import com.diplback.diplserver.model.User
import com.diplback.diplserver.repository.MessageRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController {

    @Autowired
    lateinit var messageRepo: MessageRepo

    @PostMapping("/send")
    fun sendMessage(@RequestBody messageDto: MessageDto): ResponseEntity<Message> {
        val message = Message(
            senderId = messageDto.senderId,
            receiverId = messageDto.receiverId,
            content = messageDto.content
        )
        val savedMessage = messageRepo.save(message)
        return ResponseEntity.ok(savedMessage)
    }

    /*@GetMapping("/history/{userId}")
    fun getChatHistory(@PathVariable userId: Int): List<Message> {
        val messages = messageRepo.findBySenderIdOrReceiverId(userId, userId)
        return messages.sortedBy { it.timestamp }
    }*/

    @GetMapping("/history/{userId}")
    fun getChatHistoryUserIds(@PathVariable userId: Int): List<Int> {
        val senderIds = messageRepo.findDistinctSenderIdsByUserId(userId)
        val receiverIds = messageRepo.findDistinctReceiverIdsByUserId(userId)
        return (senderIds + receiverIds).distinct()
    }

    @GetMapping("/history/{userId}/chat/{otherUserId}")
    fun getChatHistory(@PathVariable userId: Int, @PathVariable otherUserId: Int): List<Message> {
        val messages = messageRepo.findBySenderIdAndReceiverId(userId, otherUserId) +
                messageRepo.findBySenderIdAndReceiverId(otherUserId, userId)
        return messages.sortedBy { it.timestamp }
    }

}
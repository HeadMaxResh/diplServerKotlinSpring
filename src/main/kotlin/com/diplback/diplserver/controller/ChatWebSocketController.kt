import com.diplback.diplserver.model.Chat
import com.diplback.diplserver.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatWebSocketController @Autowired constructor(@field:Autowired private val chatService: ChatService) {
    @MessageMapping("/chat/{senderIDString}/{receiverIDString}")
    @SendTo("/topics/event/{senderIDString}/{receiverIDString}", "/topics/event/{receiverIDString}/{senderIDString}")
    fun getChatWithReceiverAndSender1(
        content: String?,
        @DestinationVariable receiverIDString: String,
        @DestinationVariable senderIDString: String,
    ): List<Chat?>? {
        val senderID: Int = Integer.parseInt(senderIDString)
        val receiverID: Int = Integer.parseInt(receiverIDString)

        chatService.save(Chat(senderId = senderID.toString(), receiverId = receiverID.toString(), content = content))
        return chatService.findBySenderOrReceiver(senderID, receiverID)
    }

    @MessageMapping("/chat/{senderIDString}/{receiverIDString}/listen")
    @SendTo("/topics/event/{senderIDString}/{receiverIDString}")
    fun subscribeToSenderReceiverChatList(
        @DestinationVariable receiverIDString: String,
        @DestinationVariable senderIDString: String,
    ): List<Chat?>? {
        val senderID: Int = Integer.parseInt(senderIDString)
        val receiverID: Int = Integer.parseInt(receiverIDString)
        return chatService.findBySenderOrReceiver(senderID, receiverID)
    }

    @MessageMapping("/hello/{receiverIDString}")
    @SendTo("/topics/event/{receiverIDString}")
    fun getChatWithReceiver(content: String?, @DestinationVariable receiverIDString: String): List<Chat?>? {
        val receiverID: Int = Integer.parseInt(receiverIDString)
        return chatService.findByReceiver(receiverID)
    }
}



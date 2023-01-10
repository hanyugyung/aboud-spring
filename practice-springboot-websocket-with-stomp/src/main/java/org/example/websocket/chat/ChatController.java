package org.example.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.example.websocket.MessagePublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessagePublisher messagePublisher;

    @MessageMapping("/chat/{id}")
    public void sendChatMessage(@PathVariable Long id, ChatMessage message) {
        messagePublisher.publish("/topic/chat/" + id, message);
    }

}

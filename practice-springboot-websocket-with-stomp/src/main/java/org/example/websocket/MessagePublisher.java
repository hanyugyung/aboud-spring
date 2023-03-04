package org.example.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.example.websocket.chat.ChatMessage;

@Component
@RequiredArgsConstructor
public class MessagePublisher {

    //private final SimpMessageSendingOperations simpMessageSendingOperations;

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(String channelTopic, ChatMessage chatMessage) {
        redisTemplate.convertAndSend(channelTopic, chatMessage);
    }
}

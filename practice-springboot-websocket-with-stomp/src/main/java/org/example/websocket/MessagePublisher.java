package org.example.websocket;

import lombok.RequiredArgsConstructor;
import org.example.websocket.chat.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagePublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(String channelTopic, ChatMessage chatMessage) {
        redisTemplate.convertAndSend(channelTopic, chatMessage);
    }
}

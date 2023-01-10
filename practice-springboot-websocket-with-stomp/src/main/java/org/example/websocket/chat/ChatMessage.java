package org.example.websocket.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String sender;
    private String text;

    private ZonedDateTime sendAt;

    @Builder
    public ChatMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.sendAt = ZonedDateTime.now();
    }
}

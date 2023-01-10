package org.example.websocket;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished , sessionId = {}", session.getId());
        Connection.addConnection(session);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Connection.getConnections().forEach((sessionId, sessionValue) -> {
            if (!sessionId.equals(session.getId())) {
                try {
                    sessionValue.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("afterConnectionClosed , sessionId = {}", session.getId());
        Connection.removeConnection(session.getId());
    }
}

package org.example.dto;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Connection {
    private static Map<String, WebSocketSession> connections = new ConcurrentHashMap<>();

    public static Map<String, WebSocketSession> getConnections(){
        return connections;
    }

    public static void addConnection(WebSocketSession session){
        connections.put(session.getId(), session);
    }

    public static void removeConnection(String Id){
        connections.remove(Id);
    }

    public static WebSocketSession getConnection(String sessionId){
        return connections.get(sessionId);
    }
}

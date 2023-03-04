package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.config.interceptor.HttpHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * sockJs 는 웹소켓을 지원하지 않는 브라우저를 위한 자바스크립트 라이브러리이다.
 * 서버에서 stomp 프로토콜을 사용하려면 클라이언트도 stomp js 를 써야한다.
 */

@Configuration
@EnableWebSocketMessageBroker
//@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configure message broker options.
     */
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws");
                //.addInterceptors(handshakeInterceptor())
                //.setHandshakeHandler(); //.withSockJS();
    }

    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                // StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                // accessor.getCommand() == "connect"
                // TODO 커넥션, 디커넥션 시 처리
                return ChannelInterceptor.super.preSend(message, channel);
            }
        });
    }

    @Bean
    public HttpHandshakeInterceptor handshakeInterceptor() {
        return new HttpHandshakeInterceptor();
    }
}

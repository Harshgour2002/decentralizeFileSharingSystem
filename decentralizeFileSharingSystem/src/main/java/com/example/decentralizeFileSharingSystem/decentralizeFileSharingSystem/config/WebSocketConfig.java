package com.example.decentralizeFileSharingSystem.decentralizeFileSharingSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Messages with prefix /topic are broadcast, /queue are peer-specific
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app"); // For client-to-server messages
        config.setUserDestinationPrefix("/user"); // For sending messages to specific peer
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint for WebSocket connection (frontend connects here)
        registry.addEndpoint("/ws/signaling")
                .setAllowedOriginPatterns("*") // allow all origins for testing
                .withSockJS(); // fallback to SockJS if WebSocket not supported
    }
}
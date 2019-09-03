package com.maryanto.dimas.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/request");
        config.enableSimpleBroker("/request/reminder", "/request/chat", "/secure/");
        config.setUserDestinationPrefix("/secure/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler());

        registry.addEndpoint("/sockjs")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler())
                .withSockJS()
                .setWebSocketEnabled(true)
                .setSessionCookieNeeded(true);
    }


}

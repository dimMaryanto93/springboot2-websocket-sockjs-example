package com.maryanto.dimas.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class MessageBrokerService {

    @Value("${server.websocket.sockjs.url}")
    private String url;

    @Value("${server.websocket.sockjs.username}")
    private String username;

    @Value("${server.websocket.sockjs.password}")
    private String password;

    @Autowired
    private WebSocketStompClient stompClient;

    public class StompSessionHandler extends StompSessionHandlerAdapter {

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            log.info("websocket connection!");
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            log.error("websocket connection error", exception);
        }

    }

    public StompSession connect() throws InterruptedException, ExecutionException, TimeoutException {
        ListenableFuture<StompSession> connect = stompClient.connect(url, new StompSessionHandler());
        StompSession stompSession = connect.get(5000, TimeUnit.MILLISECONDS);
        return stompSession;
    }
}

package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.MessageDto;
import com.maryanto.dimas.example.service.MessageBrokerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class TestChatMessageBroker {

    @Autowired
    private MessageBrokerService service;

    @PostMapping("/api/chat/request")
    public ResponseEntity<?> requestChat(@RequestBody MessageDto.ChatRequest request) {
        try {
            StompHeaders headers = new StompHeaders();
            headers.setDestination("/request/chat");

//            TODO open connection
            StompSession session = service.connect();
            StompSession.Receiptable send = session.send(headers, request);
//            TODO close session after used
            session.disconnect();

            Map<String, Object> response = new HashMap<>();
            response.put("sendId", send);
            return ok(send);
        } catch (InterruptedException e) {
            log.info("connection interrupted ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            log.info("execution", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (TimeoutException e) {
            log.info("timeout", e);
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }
}

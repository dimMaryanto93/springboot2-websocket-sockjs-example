package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.ChatDto;
import com.maryanto.dimas.example.mapper.ChatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class ChatBrokerController {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/chat")
    public void chatToUser(@Payload ChatDto.Request dto) {
        ChatDto.Response response = ChatMapper.ChatResponseRequest.instance.convertToEntity(dto);
        response.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));

        log.info("message sending: {}", response);
        this.messageTemplate.convertAndSendToUser(response.getToId(), "/chat/send", response);
    }

}

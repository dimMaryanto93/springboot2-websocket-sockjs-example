package com.maryanto.dimas.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MessageDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatRequest {

        private String toId;
        private String fromId;
        private String message;
    }
}

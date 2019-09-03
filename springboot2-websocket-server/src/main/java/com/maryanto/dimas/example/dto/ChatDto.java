package com.maryanto.dimas.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class ChatDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        private String toId;
        private String fromId;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {

        private String id;
        private String toId;
        private String fromId;
        private String message;
        private Timestamp dateCreated;

    }
}

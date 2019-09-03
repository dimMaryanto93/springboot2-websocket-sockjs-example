package com.maryanto.dimas.example.mapper;

import com.maryanto.dimas.example.dto.ChatDto;
import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public class ChatMapper {

    @Mapper
    public interface ChatResponseRequest extends ObjectMapper<ChatDto.Response, ChatDto.Request> {
        ChatResponseRequest instance = Mappers.getMapper(ChatResponseRequest.class);

        @Override
        ChatDto.Request convertToDto(ChatDto.Response response);

        @Override
        @Mappings(value = {
                @Mapping(target = "dateCreated", ignore = true)
        })
        ChatDto.Response convertToEntity(ChatDto.Request request);
    }
}

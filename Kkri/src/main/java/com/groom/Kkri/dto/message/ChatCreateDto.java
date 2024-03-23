package com.groom.Kkri.dto.message;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatCreateDto {
    Long chatRoomId;
    Long senderId;
    String message;

    @Builder
    public ChatCreateDto(Long chatRoomId, Long senderId, String message){
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.message = message;
    }
}

package com.groom.Kkri.dto.message;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMsgAndRoomDto {
    Long boarderId;
    Long senderId;
    String message;

    @Builder
    public ChatMsgAndRoomDto(Long boarderId, Long senderId, String message){
        this.boarderId = boarderId;
        this.senderId = senderId;
        this.message = message;
    }
}

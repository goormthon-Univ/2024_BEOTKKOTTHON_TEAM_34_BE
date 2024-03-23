package com.groom.Kkri.dto.message;

import com.groom.Kkri.entity.ChatMessage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageDto {
    private Long senderId;
    private String content;

    @Builder
    public ChatMessageDto(ChatMessage chatMessage) {
        this.senderId = chatMessage.getSenderId();
        this.content = chatMessage.getContent();
    }
}


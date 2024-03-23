package com.groom.Kkri.chatmessage;

import com.groom.Kkri.entity.BaseTimeEntity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String chatRoomId;
    private String senderId;
    private String recipientId;
    private String content;

}

package com.groom.Kkri.dto.chatroom;

import com.groom.Kkri.entity.ChatRoom;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChatRoomSimpleDto {
    private String otherNickname;
    private String lastMessage;
    private LocalDateTime time;
    private Long roomId;
    private Long boardId;

    @Builder
    public ChatRoomSimpleDto(ChatRoom chatRoom,String otherNickname){
        this.otherNickname = otherNickname;
        this.lastMessage = chatRoom.getLatestMessage();
        this.time = chatRoom.getLastModifiedDate();
        this.roomId = chatRoom.getId();
        this.boardId = chatRoom.getBoard().getId();
    }
}

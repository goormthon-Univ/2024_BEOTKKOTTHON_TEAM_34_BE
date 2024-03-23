package com.groom.Kkri.dto.chatroom;

import com.groom.Kkri.entity.ChatRoom;
import com.groom.Kkri.enums.State;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRoomDetailDto {

    private String otherNickname;
    private Long roomId;
    private State state;
    private Long boardId;

    @Builder
    public ChatRoomDetailDto(ChatRoom chatRoom, String otherNickname){
        this.roomId = chatRoom.getId();
        this.otherNickname = otherNickname;
        this.state = chatRoom.getBoard().getState();
        this.boardId = chatRoom.getBoard().getId();
    }

}

package com.groom.Kkri.dto;

import com.groom.Kkri.entity.Board;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BoardIndividualDto {
    Long id;
    String title;
    Long point;
    int chatRoomsCount;
    LocalDateTime createdDate;

    BoardIndividualDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.point = board.getExchangePoint();
        this.chatRoomsCount = board.getChatRooms().size();
        this.createdDate = board.getCreatedDate();
    }
}

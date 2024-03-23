package com.groom.Kkri.dto.board;

import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.entity.Attachment;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.Type;
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
    Type type;

    AttachmentOutputDto attachmentOutputDto;

    public BoardIndividualDto(Board board, AttachmentOutputDto attachmentOutputDto){
        this.id = board.getId();
        this.title = board.getTitle();
        this.point = board.getExchangePoint();
        this.chatRoomsCount = board.getChatRooms().size();
        this.createdDate = board.getCreatedDate();
        this.type = board.getType();
        this.attachmentOutputDto = attachmentOutputDto;
    }
}

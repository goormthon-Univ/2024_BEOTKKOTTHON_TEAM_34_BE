package com.groom.Kkri.dto.board;

import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardDetailDto {
    private String title;
    private String description;
    private String name;
    private LocalDateTime time;
    private Long point;

    private List<AttachmentOutputDto> images;

    @Builder
    public BoardDetailDto(Board board, List<AttachmentOutputDto> images){
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.name = board.getMember().getNickname();
        this.time = board.getCreatedDate();
        this.point = board.getExchangePoint();

        this.images = images;
    }

}

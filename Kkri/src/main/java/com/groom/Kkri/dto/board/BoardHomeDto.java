package com.groom.Kkri.dto.board;

import com.groom.Kkri.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardHomeDto {
    String title;
    Long point;

    @Builder
    public BoardHomeDto(Board board){
        this.title = board.getTitle();
        this.point = board.getExchangePoint();
    }
}

package com.groom.Kkri.dto;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long id;
    private String title;
    private String description;
    private Type type;
    private State state;
    private Long exchangePoint;
    private Long writerId;
    public static BoardDTO from(Board board) {
        return new BoardDTO(
                board.getId(),
                board.getTitle(),
                board.getDescription(),
                board.getType(),
                board.getState(),
                board.getExchangePoint(),
                board.getMember().getId()
        );
    }

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .description(this.description)
                .type(this.type)
                .state(this.state)
                .build();
    }

}
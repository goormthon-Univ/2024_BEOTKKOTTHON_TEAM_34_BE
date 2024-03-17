package com.groom.Kkri.dto;

import com.groom.Kkri.entity.Board.Type;
import com.groom.Kkri.entity.Board.State;
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
}
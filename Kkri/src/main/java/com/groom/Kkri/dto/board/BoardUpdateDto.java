package com.groom.Kkri.dto.board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardUpdateDto {
    String title;
    Long exchangePoint;
    String description;
}

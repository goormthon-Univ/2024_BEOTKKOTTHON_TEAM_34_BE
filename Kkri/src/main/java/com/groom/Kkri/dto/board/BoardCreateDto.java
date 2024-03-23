package com.groom.Kkri.dto.board;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardCreateDto {

    private String title;
    private String description;
    private Type type;
    private State state;
    private Long exchangePoint;

//    private List<MultipartFile> images;

    public Board createBoard(Member member){
        return Board.builder()
                .member(member)
                .exchangePoint(exchangePoint)
                .description(description)
                .title(title)
                .state(state)
                .type(type)
                .build();
    }
}

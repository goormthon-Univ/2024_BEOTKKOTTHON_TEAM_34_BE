package com.groom.Kkri.dto;

import com.groom.Kkri.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardPageDto {


    int currentPage=0;
    List<BoardIndividualDto> list = new ArrayList<>();

    public BoardPageDto(Slice<Board> boards){
        for(var s : boards.getContent()){
            BoardIndividualDto board = new BoardIndividualDto(s);
            list.add(board);
        }
        currentPage = boards.getNumber();
    }

}

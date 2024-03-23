package com.groom.Kkri.dto.board;

import com.groom.Kkri.dto.board.BoardIndividualDto;
import com.groom.Kkri.entity.Board;
import lombok.Data;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardPageDto {


    int currentPage=0;
    List<BoardIndividualDto> list = new ArrayList<>();

    public BoardPageDto(List<BoardIndividualDto> list, int currentPage){
        this.list = list;
        this.currentPage = currentPage;
    }

}

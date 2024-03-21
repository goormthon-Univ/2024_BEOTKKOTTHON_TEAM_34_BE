package com.groom.Kkri.service;

import com.groom.Kkri.dto.BoardPageDto;
import com.groom.Kkri.enums.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void test(){
        PageRequest pageRequest = PageRequest.of(0,3);

        BoardPageDto board = boardService.getBoard(Type.HELPED, pageRequest);

        for(var s : board.getList()){
            System.out.println(s);
        }



    }
}
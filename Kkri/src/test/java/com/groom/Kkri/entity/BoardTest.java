package com.groom.Kkri.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardTest {

    @Test
    public void makeTest(){
        Member member1 = Member.builder()
                .id(1L)
                .univ("univ")
                .nickname("jaejae")
                .point(2L)
                .password("skdfjhsdk")
                .username("zhsdfa")
                .build();


        Board board = Board.builder()
                .id(1L)
                .title("skadhfk")
                .description("hi")
                .exchangePoint(2L)
                .type(Board.Type.HELPED)
                .state(Board.State.PRE_DEAL)
                .member(member1)
                .build();

        Assertions.assertThat(member1.getUniv()).isEqualTo("univ");
        Assertions.assertThat(board.getDescription()).isEqualTo("hi");
    }
}
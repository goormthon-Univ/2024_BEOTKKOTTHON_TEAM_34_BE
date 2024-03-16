package com.groom.Kkri.entity;

import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
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

                .univ("univ")
                .nickname("jaejae")
                .point(2L)
                .password("skdfjhsdk")
                .username("zhsdfa")
                .build();


        Board board = Board.builder()

                .title("skadhfk")
                .description("hi")
                .exchangePoint(2L)
                .type(Type.HELPED)
                .state(State.PRE_DEAL)
                .member(member1)
                .build();

        Assertions.assertThat(member1.getUniv()).isEqualTo("univ");
        Assertions.assertThat(board.getDescription()).isEqualTo("hi");
    }
}
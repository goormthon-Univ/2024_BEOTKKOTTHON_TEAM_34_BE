package com.groom.Kkri.entity;

import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.repository.BoardRepository;
import com.groom.Kkri.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardTest {
    @Autowired
    private BoardService boardService;
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

    @Test
    void updateBoard(){
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

        Board board2 = Board.builder()

                .title("진자 재밌음")
                .description("컴온")
                .exchangePoint(5L)
                .type(Type.HELPED)
                .state(State.PRE_DEAL)
                .member(member1)
                .build();

        board.updateBoard("진자 재밌음","컴온",5L);
        Assertions.assertThat(board.toString()).isEqualTo(board2.toString());
    }
    @Test
    public void writePost(){
        Member member1 = Member.builder()
                .univ("구름대")
                .nickname("hoho")
                .point(4L)
                .password("1234")
                .username("이호연")
                .build();

        Board board = Board.builder()

                .title("바퀴벌레 잡아주실분...")
                .description("바선생이랑 눈마주친 거 같아요 제발 잡아주세요 ㅠㅠ")
                .exchangePoint(2L)
                .type(Type.HELPED)
                .state(State.PRE_DEAL)
                .member(member1)
                .build();

        Assertions.assertThat(member1.getUniv()).isEqualTo("구름대");
        Assertions.assertThat(board.getTitle()).isEqualTo("바퀴벌레 잡아주실분...");
        Assertions.assertThat(board.getType()).isEqualTo(Type.HELPED);
    }

    @Test
    public void deletePost() {
        // Given: 게시물 작성
        Member member1 = Member.builder()
                .univ("구름대")
                .nickname("hoho")
                .point(4L)
                .password("1234")
                .username("이호연")
                .build();

        Board board = Board.builder()
                .title("바퀴벌레 잡아주실분...")
                .description("바선생이랑 눈마주친 거 같아요 제발 잡아주세요 ㅠㅠ")
                .exchangePoint(2L)
                .type(Type.HELPED)
                .state(State.PRE_DEAL)
                .member(member1)
                .build();


        // When: 게시물 삭제
//        boardService.deletePost(board.getType(), board.getId());
//
//        // Then: 삭제된 게시물이 조회되지 않음
//        Optional<Board> deletedBoardOptional = boardService.getPostById(board.getId());
//        assertFalse(deletedBoardOptional.isPresent(), "삭제된 게시물이 조회되어야 합니다.");
    }

}
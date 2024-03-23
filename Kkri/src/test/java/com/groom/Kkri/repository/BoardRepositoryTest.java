package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void test(){

        Optional<Member> sim = memberRepository.findByUsername("sim");
        Optional<Member> jae = memberRepository.findByUsername("jae");

        System.out.println(sim.get().getNickname());
        System.out.println(sim.get().getBoards());

        Board board1 = Board
                .builder()
                .description("안녕")
                .exchangePoint(3L)
                .state(State.PRE_DEAL)
                .type(Type.HELPED)
                .title("일루와")
                .member(sim.get())
                .build();


        Board board2 = Board
                .builder()
                .description("롤 할 사람")
                .exchangePoint(2L)
                .state(State.POST_DEAL)
                .type(Type.HELPED)
                .title("컴온")
                .member(sim.get())
                .build();


        Board board3 = Board
                .builder()
                .description("롤 할 사람")
                .exchangePoint(2L)
                .state(State.PRE_DEAL)
                .type(Type.HELPED)
                .title("컴온")
                .member(jae.get())
                .build();


        Board board4 = Board
                .builder()
                .description("안녕")
                .exchangePoint(3L)
                .state(State.PRE_DEAL)
                .type(Type.HELPING)
                .title("일루와")
                .member(sim.get())
                .build();



        Board board5 = Board
                .builder()
                .description("롤 할 사람")
                .exchangePoint(2L)
                .state(State.PRE_DEAL)
                .type(Type.HELPING)
                .title("컴온")
                .member(jae.get())
                .build();



        Board board6 = Board
                .builder()
                .description("롤 할 사람")
                .exchangePoint(2L)
                .state(State.POST_DEAL)
                .type(Type.HELPING)
                .title("컴온")
                .member(jae.get())
                .build();


        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
        boardRepository.save(board5);
        boardRepository.save(board6);

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));


        Slice<Board> byBoardLimit5 = boardRepository.findByBoardLimit5(Type.HELPED, pageRequest);

        for( var s : byBoardLimit5.getContent()){
            System.out.println(s);
        }


    }

    @Test
    void dateTest(){
        Optional<Member> jae = memberRepository.findByUsername("jae");


        Board board1 = Board
                .builder()
                .description("~~드루와~~")
                .exchangePoint(3L)
                .state(State.PRE_DEAL)
                .type(Type.HELPED)
                .title("몰라")
                .member(jae.get())
                .build();
        boardRepository.save(board1);

        System.out.println("here" + board1.getCreatedDate());
    }

    @Test
    void queryTest(){
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));

        Optional<Member> sim = memberRepository.findByUsername("sim");
        Slice<Board> byUserBoard = boardRepository.findByUserBoard(sim.get().getId(), Type.HELPED, pageRequest);

        for(var s : byUserBoard.getContent()){
            System.out.println(s.getTitle());
        }

        Slice<Board> byTitleContainingOrDescriptionContaining = boardRepository.findByTitleContainingOrDescriptionContaining("와", "롤", pageRequest);

        for(var s : byTitleContainingOrDescriptionContaining.getContent()){
            System.out.println(s.getTitle());
        }

        Slice<Board> byType = boardRepository.findByType(Type.HELPING, pageRequest);
        for (var s : byType.getContent()) {
            System.out.println(s.getTitle());
        }

    }
}
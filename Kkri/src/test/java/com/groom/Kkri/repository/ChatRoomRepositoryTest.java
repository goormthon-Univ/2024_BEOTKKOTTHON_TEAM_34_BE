package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.entity.ChatRoom;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChatRoomRepositoryTest {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    void queryTest(){



        ChatRoom room = chatRoomRepository.findByChatRoom(9L, 4L);

        System.out.println(room);
    }

    @Test
//    @Rollback(value = false)
    void test3(){

        Optional<Member> sim = memberRepository.findByUsername("sim");
        Optional<Member> jae = memberRepository.findByUsername("jae");

        Optional<Board> board1 = boardRepository.findById(7L);
        Optional<Board> board2 = boardRepository.findById(8L);
        Optional<Board> board3 = boardRepository.findById(9L); // jae


        ChatRoom chatRoom1 = ChatRoom.builder()
                .latestMessage("안녕하세요")
                .sender(sim.get())
                .board(board3.get())
                .build();

        ChatRoom chatRoom2 = ChatRoom.builder()
                .latestMessage("굿굿")
                .sender(jae.get())
                .board(board1.get())
                .build();

        ChatRoom chatRoom3 = ChatRoom.builder()
                .latestMessage("굿굿")
                .sender(jae.get())
                .board(board2.get())
                .build();

        chatRoomRepository.save(chatRoom1);
        chatRoomRepository.save(chatRoom2);
        chatRoomRepository.save(chatRoom3);

        List<ChatRoom> roomList = chatRoomRepository.findChatRoomByUserId(4L);

        Assertions.assertThat(roomList).containsExactly(chatRoom1,chatRoom2,chatRoom3);
    }

    @Test
    @Rollback(value = false)
    void test(){
        Member member1 = Member.builder()
                .univ("univ")
                .point(3L)
                .nickname("minam")
                .password("1234")
                .username("sim")
                .build();

        Member member2 = Member.builder()
                .univ("univ")
                .point(3L)
                .nickname("gosu")
                .password("2345")
                .username("jae")
                .build();

        Member member3 = Member.builder()
                .univ("univ")
                .point(3L)
                .nickname("note")
                .password("3456")
                .username("jaejae")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

    }
    @Test
    @Rollback(value = false)
    public void test2(){

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
}
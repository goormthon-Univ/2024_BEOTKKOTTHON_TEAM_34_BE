package com.groom.Kkri.repository;

import com.groom.Kkri.entity.ChatMessage;
import com.groom.Kkri.entity.ChatRoom;
import com.groom.Kkri.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChatMessageRepositoryTest {
    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Test
    @Rollback(value = false)
    void test(){
        ChatRoom chatRoom = chatRoomRepository.findById(4L).get();
        Member sender = chatRoom.getSender();
        Member receiver = chatRoom.getReceiver();

        ChatMessage message1 = ChatMessage.builder()
                .chatRoom(chatRoom)
                .content("안녕하세요!")
                .senderId(sender.getId())
                .build();
        ChatMessage message2 = ChatMessage.builder()
                .chatRoom(chatRoom)
                .content("제가 도와드릴게요")
                .senderId(sender.getId())
                .build();
        ChatMessage message3 = ChatMessage.builder()
                .chatRoom(chatRoom)
                .content("감사합니다!")
                .senderId(receiver.getId())
                .build();

        chatMessageRepository.save(message1);
        chatMessageRepository.save(message2);
        chatMessageRepository.save(message3);

        Sort sort = Sort.by(Sort.Direction.ASC,"createdDate");
        for( var s :chatMessageRepository.findByChatRoomId(chatRoom.getId(),sort)){
            System.out.println(s.getSenderId() + " " + s.getContent());
        }


    }
}
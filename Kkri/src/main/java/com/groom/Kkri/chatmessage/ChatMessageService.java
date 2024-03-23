//package com.groom.Kkri.chatmessage;
//
//import com.groom.Kkri.entity.ChatRoom;
//import com.groom.Kkri.repository.ChatRoomRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ChatMessageService {
//    private final ChatMessageRepository chatMessageRepository;
//    private final ChatRoomRepository chatRoomRepository;
//
//    public ChatMessage save(ChatMessage chatMessage){
//         chatMessageRepository.save(chatMessage);
//         return chatMessage;
//    }
//    public List<ChatMessage> findChatMessages(Long roomId){
//        return chatMessageRepository.findByChatRoomId(roomId);
//    }
//
//}

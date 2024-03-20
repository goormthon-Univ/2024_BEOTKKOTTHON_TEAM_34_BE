package com.groom.Kkri.chatmessage;

import com.groom.Kkri.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

//    @MessageMapping("/chat.send")
//    public void sendMessage(@Payload ChatMessage chatMessage){
//        String chatRoomId = chatMessage.getChatRoomId();
//        String destination = "/topic" + chatRoomId;
//
//        messageService.save(chatMessage);
//
//        //room의 최근 대화내역 업데이트
//        //채팅의 종류의 따라 처리하기
//
//        messagingTemplate.convertAndSend(destination,chatMessage);
//    }
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        messageService.save(chatMessage);
        return chatMessage;
    }

    @GetMapping("/messages/{chatRoomId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("chatRoomId") String id){
        Long roomId = Long.parseLong(id);
        return ResponseEntity.ok(messageService.findChatMessages(roomId));
    }

}

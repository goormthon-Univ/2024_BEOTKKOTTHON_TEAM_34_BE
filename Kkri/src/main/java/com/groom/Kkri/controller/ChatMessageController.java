package com.groom.Kkri.controller;

import com.groom.Kkri.chatmessage.ChatMessage;
import com.groom.Kkri.dto.BaseResponse;
import com.groom.Kkri.dto.message.ChatMessageDto;
import com.groom.Kkri.dto.message.ChatCreateDto;
import com.groom.Kkri.service.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "message")
@RequestMapping("/api/message")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

//    @GetMapping("/{chatRoomId}")
//    @Operation(summary = "채팅 내역 가져오기")
//    public ResponseEntity<List<ChatMessageDto>> getMessages(@RequestParam("chatRoomId") Long chatRoomId){
//        return ResponseEntity.ok(chatMessageService.getMessage(chatRoomId));
//    }

//    @PostMapping
//    @Operation(summary = "채팅방 만들면서 채팅 보내기")
//    public ResponseEntity<?> createRoomAndChat(@RequestBody ChatCreateDto chatCreateDto){
//
//    }
//
//    @PostMapping
//    @Operation(summary = "채팅 만들기")
//    public ResponseEntity<?> createMessage(@RequestBody ChatCreateDto chatCreateDto){
//        return chatMessageService.createMessage(chatCreateDto);
//    }


    ////



//    @MessageMapping("/chat.send")
//    @SendTo("/topic/public")
//    public ChatCreateDto sendMessage(@Payload ChatCreateDto chatMessage){
//        chatMessageService.createMessage(chatMessage);
//        return chatMessage;
//    }

//    @MessageMapping("/chat.register")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage) {
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

    @MessageMapping("/chat/{roomId}.send")
    @SendTo("/topic/chat/{roomId}")
    public ChatCreateDto sendMessage(@Payload ChatCreateDto chatMessage,@DestinationVariable("roomId") String roomId){
        chatMessageService.createMessage(chatMessage);
        return chatMessage;
    }
    @GetMapping("/messages/{chatRoomId}")
    public BaseResponse<?> findChatMessages(
            @PathVariable("chatRoomId") Long chatRoomId){

        return BaseResponse.response(chatMessageService.getMessage(chatRoomId));
    }
}

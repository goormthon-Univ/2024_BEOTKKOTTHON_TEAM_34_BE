//package com.groom.Kkri.controller;
//
//import com.groom.Kkri.dto.message.ChatMessageDto;
//import com.groom.Kkri.dto.message.ChatCreateDto;
//import com.groom.Kkri.service.ChatMessageService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@Tag(name = "message")
//@RequestMapping("/api/message")
//public class ChatMessageController {
//    private final ChatMessageService chatMessageService;
//
//    @GetMapping("/{chatRoomId}")
//    @Operation(summary = "채팅 내역 가져오기")
//    public ResponseEntity<List<ChatMessageDto>> getMessages(@RequestParam("chatRoomId") Long chatRoomId){
//        return ResponseEntity.ok(chatMessageService.getMessage(chatRoomId));
//    }
//
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
//}

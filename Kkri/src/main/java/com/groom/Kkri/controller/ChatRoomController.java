package com.groom.Kkri.controller;

import com.groom.Kkri.dto.BaseResponse;
import com.groom.Kkri.dto.chatroom.ChatRoomDetailDto;
import com.groom.Kkri.dto.chatroom.ChatRoomSimpleDto;
import com.groom.Kkri.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "채팅 룸")
@RequestMapping("/api")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chatroom/board")
    @Operation(summary = "board와 관련 채팅룸 요청")
    public BaseResponse<ChatRoomDetailDto> getChatByBoardIdAndSenderId(@RequestParam("boardId")Long boardId , @RequestParam("senderId")Long senderId){
        return BaseResponse.response(chatRoomService.getChatRoomSendId(boardId, senderId));
    }

    @GetMapping("/chatroom/room")
    @Operation(summary = "roomid 관련 채팅룸 요청")
    public BaseResponse<ChatRoomDetailDto> getChatByRoomId(@RequestParam("roomId")Long roomId , @RequestParam("userId")Long userId){
        return BaseResponse.response(chatRoomService.getChatRoom(roomId,userId));
    }

    @GetMapping("/chatroom/list/user")
    @Operation(summary = "user애 관한 채팅룸 리스트 관련 채팅 요청")
    public BaseResponse<List<ChatRoomSimpleDto>> getChatRoomsByUserId(@RequestParam("userId") Long userId){
        return BaseResponse.response(chatRoomService.getChatRoomByUserId(userId));
    }

    @GetMapping("/chatroom/list/board")
    @Operation(summary = "board와 관련 채팅룸 리스트 요청")
    public BaseResponse<List<ChatRoomSimpleDto>> getChatRoom(@RequestParam("boardId") Long boardId, @RequestParam("userId") Long userId){
        return BaseResponse.response(chatRoomService.getChatRoomByBoardId(boardId,userId));
    }

}

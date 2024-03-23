package com.groom.Kkri.service;

import com.groom.Kkri.dto.chatroom.ChatRoomDetailDto;
import com.groom.Kkri.dto.chatroom.ChatRoomSimpleDto;
import com.groom.Kkri.dto.message.ChatMsgAndRoomDto;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.entity.ChatRoom;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.repository.BoardRepository;
import com.groom.Kkri.repository.ChatRoomRepository;
import com.groom.Kkri.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //가져오기 2개

    public ChatRoomDetailDto getChatRoomSendId(Long boardId, Long senderId){

        ChatRoom room = chatRoomRepository.findByChatRoom(boardId, senderId).get();

        String otherNickname = getOtherNickname(senderId,room);

        return ChatRoomDetailDto.builder()
                .chatRoom(room)
                .otherNickname(otherNickname)
                .build();
    }
    public ChatRoomDetailDto getChatRoom(Long roomId, Long userId){
        ChatRoom room = chatRoomRepository.findById(roomId).get();

        String otherNickname = getOtherNickname(userId, room);

        return ChatRoomDetailDto.builder()
                .chatRoom(room)
                .otherNickname(otherNickname)
                .build();
    }



    public List<ChatRoomSimpleDto> getChatRoomByUserId(Long userId){
        List<ChatRoomSimpleDto> result = new ArrayList<>();
        for (ChatRoom chatRoom : chatRoomRepository.findChatRoomByUserId(userId)) {
            String otherNickname = getOtherNickname(userId, chatRoom);
            result.add(new ChatRoomSimpleDto(chatRoom,otherNickname));
        }
        return result;
    }

    public List<ChatRoomSimpleDto> getChatRoomByBoardId(Long boardId, Long userId){
        List<ChatRoomSimpleDto> result = new ArrayList<>();
        for (ChatRoom chatRoom : chatRoomRepository.findChatRoomByBoardId(boardId)) {
            String otherNickname = getOtherNickname(userId, chatRoom);
            result.add(new ChatRoomSimpleDto(chatRoom,otherNickname));
        }
        return result;
    }



    public ChatRoom createRoom(ChatMsgAndRoomDto chatMsgAndRoomDto){
        Long boarderId = chatMsgAndRoomDto.getBoarderId();
        Long senderId = chatMsgAndRoomDto.getSenderId();
        Board board = boardRepository.findById(boarderId).get();
        Member member = memberRepository.findById(senderId).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .board(board)
                .sender(member)
                .latestMessage(chatMsgAndRoomDto.getMessage())
                .build();

        return chatRoomRepository.save(chatRoom);
    }

    private String getOtherNickname(Long userId, ChatRoom room) {
        String otherNickName = "";

        if(Objects.equals(room.getSender().getId(), userId)){
            otherNickName = room.getReceiver().getNickname();
        }
        else if(Objects.equals(room.getReceiver().getId(), userId)){
            otherNickName = room.getSender().getNickname();
        }
        return otherNickName;
    }

}


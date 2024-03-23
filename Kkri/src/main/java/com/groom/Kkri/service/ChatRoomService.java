package com.groom.Kkri.service;

import com.groom.Kkri.dto.chatroom.ChatRoomDetailDto;
import com.groom.Kkri.dto.chatroom.ChatRoomSimpleDto;
import com.groom.Kkri.dto.message.ChatMsgAndRoomDto;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.entity.ChatRoom;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.repository.BoardRepository;
import com.groom.Kkri.repository.ChatRoomRepository;
import com.groom.Kkri.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public boolean exchangePoint(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();

        Member sender = chatRoom.getSender();
        Member receiver = chatRoom.getReceiver();
        Long exchangePoint = chatRoom.getBoard().getExchangePoint();

        if(chatRoom.getBoard().getState().equals(State.POST_DEAL)){
            return false;
        }

        if(sender.getPoint() < exchangePoint || receiver.getPoint() < exchangePoint){
            return false;
        }

        if(chatRoom.getBoard().getType().equals(Type.HELPED)){
            sender.helping(exchangePoint);
            receiver.helped(exchangePoint);
        }
        else{
            receiver.helping(exchangePoint);
            sender.helped(exchangePoint);
        }

        chatRoom.getBoard().setState(State.POST_DEAL);

        return true;
    }

    @Transactional
    public boolean exchangeState(Long roomId, State state) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        Board board = chatRoom.getBoard();

        if(board.getState().equals(State.POST_DEAL)){
            return false;
        }
        board.setState(state);

        return true;
    }

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

    public Long createRoom(Long boardId, Long senderId){
        Board board = boardRepository.findById(boardId).get();
        Member member = memberRepository.findById(senderId).get();

        ChatRoom room = ChatRoom.builder()
                .sender(member)
                .board(board)
                .build();
        ChatRoom save = chatRoomRepository.save(room);
        return save.getId();
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


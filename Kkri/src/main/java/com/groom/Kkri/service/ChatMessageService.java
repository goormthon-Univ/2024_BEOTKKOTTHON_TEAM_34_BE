package com.groom.Kkri.service;

import com.groom.Kkri.dto.message.ChatMessageDto;
import com.groom.Kkri.dto.message.ChatCreateDto;
import com.groom.Kkri.dto.message.ChatMsgAndRoomDto;
import com.groom.Kkri.entity.ChatMessage;
import com.groom.Kkri.entity.ChatRoom;
import com.groom.Kkri.repository.ChatMessageRepository;
import com.groom.Kkri.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;

    public List<ChatMessageDto> getMessage(Long chatRoomId){
        return chatMessageRepository.findByChatRoomId(chatRoomId, Sort.by(Sort.Direction.ASC,"createdDate")).stream()
                .map(ChatMessageDto::new)
                .toList();
    }

    @Transactional
    public ChatMessageDto createMessage(ChatCreateDto chatCreateDto){

        Long chatRoomId = chatCreateDto.getChatRoomId();
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .senderId(chatCreateDto.getSenderId())
                .content(chatCreateDto.getContent())
                .build();

        chatMessageRepository.save(chatMessage);

        chatRoom.updateLatestMessage(chatCreateDto.getContent());

        return new ChatMessageDto(chatMessage);
    }

    @Transactional
    public ChatMessageDto createRoomAndMessage(ChatMsgAndRoomDto chatMsgAndRoomDto){
        ChatRoom room = chatRoomService.createRoom(chatMsgAndRoomDto);

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(room)
                .senderId(chatMsgAndRoomDto.getSenderId())
                .content(chatMsgAndRoomDto.getMessage())
                .build();

        chatMessageRepository.save(chatMessage);

        room.updateLatestMessage(chatMsgAndRoomDto.getMessage());

        return new ChatMessageDto(chatMessage);
    }
}

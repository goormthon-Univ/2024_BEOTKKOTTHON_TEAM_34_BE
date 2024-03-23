package com.groom.Kkri.repository;

import com.groom.Kkri.entity.ChatMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    @Query("select m from ChatMessage m join fetch m.chatRoom r where r.id= :roomId")
    List<ChatMessage> findByChatRoomId(@Param("roomId")Long roomId, Sort sort);
}

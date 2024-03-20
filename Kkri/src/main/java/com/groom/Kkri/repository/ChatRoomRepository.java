package com.groom.Kkri.repository;

import com.groom.Kkri.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    @Query("select c from ChatRoom c join fetch c.board b join fetch c.sender s where b.id= :board_id and s.id = :sender_id")
    ChatRoom findByChatRoom(@Param("board_id") Long board_id, @Param("sender_id") Long sender_id);

    @Query("select c from ChatRoom c join fetch c.sender s join fetch c.receiver r where s.id = :id or r.id = :id")
    List<ChatRoom> findChatRoomByUserId(@Param("id") Long id);
}

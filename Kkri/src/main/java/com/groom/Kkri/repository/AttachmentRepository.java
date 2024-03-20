package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    @Query("select a from Attachment a join fetch a.board b where b.id= :boardId")
    List<Attachment> findByBoardId(@Param("boardId") Long id);
}

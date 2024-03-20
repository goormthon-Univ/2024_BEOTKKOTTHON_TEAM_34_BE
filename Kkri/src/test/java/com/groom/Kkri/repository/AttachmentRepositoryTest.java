package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Attachment;
import com.groom.Kkri.entity.Board;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AttachmentRepositoryTest {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Rollback(value = false)
    void test(){

        Optional<Board> board1 = boardRepository.findById(7L);
        Optional<Board> board2 = boardRepository.findById(8L);

        Attachment attachment1 = Attachment.builder()
                .storeFileName("/store")
                .uploadFileName("/upload")
                .board(board1.get())
                .build();

        Attachment attachment2 = Attachment.builder()
                .storeFileName("/store/store")
                .uploadFileName("/upload/upload")
                .board(board1.get())
                .build();
        Attachment attachment3 = Attachment.builder()
                .storeFileName("/store")
                .uploadFileName("/upload")
                .board(board2.get())
                .build();

        Attachment attachment4 = Attachment.builder()
                .storeFileName("/store/store")
                .uploadFileName("/upload/upload")
                .board(board2.get())
                .build();

        attachmentRepository.save(attachment1);
        attachmentRepository.save(attachment2);
        attachmentRepository.save(attachment3);
        attachmentRepository.save(attachment4);

        List<Attachment> byBoardId = attachmentRepository.findByBoardId(7L);
        Assertions.assertThat(byBoardId).containsExactly(attachment1, attachment2);
    }
}
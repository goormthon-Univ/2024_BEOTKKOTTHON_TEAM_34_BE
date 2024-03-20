package com.groom.Kkri.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Attachment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String uploadFileName;
    private String storeFileName;

    @Builder
    public Attachment(Board board, String uploadFileName, String storeFileName){
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        setBoard(board);
    }
    private void setBoard(Board board){
        this.board = board;
        board.getAttachments().add(this);
    }

}

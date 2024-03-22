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
    private String s3url;

    @Builder
    public Attachment(Board board, String uploadFileName, String storeFileName,String s3url){
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.s3url = s3url;
        setBoard(board);
    }
    private void setBoard(Board board){
        this.board = board;
        board.getAttachments().add(this);
    }

    public void setStoreFileName(String fileName){
        this.storeFileName = fileName;
    }
    public void setS3url(String s3url) { this.s3url = s3url; }

}

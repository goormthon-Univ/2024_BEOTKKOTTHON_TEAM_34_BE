package com.groom.Kkri.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"id","latestMessage"})
public class ChatRoom extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_member_id")
    private Member sender;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_member_id")
    private Member receiver;

    private String latestMessage;

    @Builder
    public ChatRoom(Board board, Member sender, String latestMessage){
        this.board = board;
        this.sender = sender;
        this.receiver = board.getMember();
        this.latestMessage = latestMessage;

        board.getChatRooms().add(this);
        sender.getChatSendRooms().add(this);
        receiver.getChatReceiveRooms().add(this);
    }

    public void updateLatestMessage(String message){
        this.latestMessage = message;
    }

}

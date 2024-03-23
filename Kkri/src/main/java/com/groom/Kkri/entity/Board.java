package com.groom.Kkri.entity;

import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","title","description","type","state","exchangePoint"})
@Getter
public class Board extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private State state;
    private Long exchangePoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms = new ArrayList<>();


    @Builder
    public Board(String title,String description, Type type, State state, Long exchangePoint, Member member){
        this.title = title;
        this.description = description;
        this.type = type;
        this.state = state;
        this.exchangePoint = exchangePoint;
        setMember(member);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void updateBoard(String title, String description,Long exchangePoint){
        this.title = title;
        this.description = description;
        this.exchangePoint = exchangePoint;
    }
    public void updateType(Type type){
        this.type = type;
    }
    public void updateState(State state){
        this.state = state;
    }
    public void setState(State state){
        this.state = state;
    }
}

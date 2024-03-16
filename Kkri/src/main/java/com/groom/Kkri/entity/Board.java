package com.groom.Kkri.entity;

import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","title","description","type","state","exchangePoint"})
@Getter
public class Board extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}

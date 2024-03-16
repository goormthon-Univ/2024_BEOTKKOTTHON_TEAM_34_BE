package com.groom.Kkri.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Board extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    @Enumerated(EnumType.STRING)
    Type type;
    @Enumerated(EnumType.STRING)
    State state;
    Long exchangePoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member writer;

    public enum Type{
        HELPING, HELPED
    }
    public enum State{
        PRE_DEAL,DEALING,POST_DEAL
    }

    @Builder
    public Board(Long id, String title,String description, Type type, State state, Long exchangePoint, Member member){
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.state = state;
        this.exchangePoint = exchangePoint;
        this.writer = member;
        member.boards.add(this);
    }



}

package com.groom.Kkri.entity;

import com.groom.Kkri.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","password","nickname","univ","point","consumePoint","earnPoint"})
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String nickname;
    private String univ;
    private Long point;

    private Long consumePoint;
    private Long earnPoint;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<ChatRoom> chatSendRooms = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<ChatRoom> chatReceiveRooms = new ArrayList<>();

    @Builder
    public Member(String username, String password, String nickname, String univ, Long point) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.univ = univ;
        this.point = point;
    }

    public void helping(Long exchangePoint){
        point += exchangePoint;
        earnPoint += exchangePoint;
    }
    public void helped(Long exchangePoint){
        point -= exchangePoint;
        consumePoint += exchangePoint;
    }
}

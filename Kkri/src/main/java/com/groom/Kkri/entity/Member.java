package com.groom.Kkri.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    Long id;
    @Column(unique = true)
    String username;
    String password;
    @Column(unique = true)
    String nickname;
    String univ;
    Long point;
    Long consumePoint;
    Long earnPoint;

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    List<Board> boards = new ArrayList<>();

    @Builder
    public Member(Long id, String username, String password, String nickname, String univ, Long point){
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.univ = univ;
        this.point = point;

    }

}

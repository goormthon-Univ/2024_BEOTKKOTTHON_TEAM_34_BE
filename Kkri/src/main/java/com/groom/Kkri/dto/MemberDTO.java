package com.groom.Kkri.dto;

import com.groom.Kkri.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private Long id;
    private String username;
    private String nickname;
    private String univ;
    private Long point;
    private Long consumePoint;
    private Long earnPoint;

    public static MemberDTO from(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getUsername(),
                member.getNickname(),
                member.getUniv(),
                member.getPoint(),
                member.getConsumePoint(),
                member.getEarnPoint()
        );
    }

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .username(this.username)
                .nickname(this.nickname)
                .univ(this.univ)
                .point(this.point)
                .build();
    }
}

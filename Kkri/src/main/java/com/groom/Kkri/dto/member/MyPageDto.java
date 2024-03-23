package com.groom.Kkri.dto.member;

import com.groom.Kkri.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageDto {
    private String nickname;
    private String univ;
    private Long point;
    private Long consumePoint;
    private Long earnPoint;

    public static MyPageDto from(Member member) {
        return MyPageDto.builder()
                .nickname(member.getNickname())
                .univ(member.getUniv())
                .point(member.getPoint())
                .consumePoint(member.getConsumePoint())
                .earnPoint(member.getEarnPoint())
                .build();
    }
}

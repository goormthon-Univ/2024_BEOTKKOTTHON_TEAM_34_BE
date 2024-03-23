package com.groom.Kkri.dto.member;

import com.groom.Kkri.entity.Member;
import lombok.*;

// 메인화면 상단 로그인 정보
@NoArgsConstructor
@Data
public class HomeUserInfoDto {
    private String nickname;
    private String univ;
    private Long point;

    @Builder
    public HomeUserInfoDto(Member member) {
        this.nickname = member.getNickname();
        this.univ = member.getUniv();
        this.point = member.getPoint();
    }
}
package com.groom.Kkri.dto.member;

import com.groom.Kkri.entity.Member;
import lombok.*;

// 메인화면 상단 로그인 정보
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInfoDto {
    private String username;
    private String nickname;
    private String univ;

    public static LoginInfoDto from(Member member) {
        LoginInfoDto loginInfoDto = new LoginInfoDto();
        loginInfoDto.setUsername(member.getUsername());
        loginInfoDto.setNickname(member.getNickname());
        loginInfoDto.setUniv(member.getUniv());
        return loginInfoDto;
    }
}
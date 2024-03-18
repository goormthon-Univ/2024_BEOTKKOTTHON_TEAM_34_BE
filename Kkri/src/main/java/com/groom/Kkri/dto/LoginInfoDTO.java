package com.groom.Kkri.dto;

import lombok.*;

// 메인화면 상단 로그인 정보
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInfoDTO {
    private String username;
    private String nickname;
    private String univ;
}

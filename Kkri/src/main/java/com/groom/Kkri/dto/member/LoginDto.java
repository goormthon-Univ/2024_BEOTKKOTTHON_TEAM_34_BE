package com.groom.Kkri.dto.member;

import com.groom.Kkri.entity.Member;
import lombok.*;

// 로그인 요청 화면
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String password;

    @Builder
    public LoginDto(Member member){
        this.username = member.getUsername();
        this.password = member.getPassword();
    }

}

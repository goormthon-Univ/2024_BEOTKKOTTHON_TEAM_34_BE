package com.groom.Kkri.controller;

import com.groom.Kkri.dto.member.LoginInfoDto;
import com.groom.Kkri.dto.member.MyPageDto;
import com.groom.Kkri.dto.member.SignUpDto;
import com.groom.Kkri.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.groom.Kkri.service.MemberService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Tag(name = "member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "로그인 api")
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean loginSuccessful = memberService.login(username, password);
        if (loginSuccessful) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    @Operation(summary = "아이디 중복 체크 api")
    @GetMapping("/idCheck")
        public String check(@RequestParam("username") String username){
        String result = null;
        boolean existsByUsername = memberService.existsByUsername(username);
        if(existsByUsername){
            System.out.println("아이디 중복");
            result = "Fail";
        }else{
            System.out.println("아이디 사용가능");
            result ="Success";
        }
        return result;
    }

    @Operation(summary = "닉네임 중복 체크 api")
    @GetMapping("/nickCheck")
    public String nickCheck(@RequestParam("nickname") String nickname){
        String result = null;
        boolean existsByNickname =  memberService.existsByNickname(nickname);
        if(existsByNickname){
            result = "Fail";
        }else {
            result = "Success";
        }
        return result;
    }

    @Operation(summary = "회원가입 api", description = "")
    @PostMapping("/join")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto) {
        boolean signupSuccessful = memberService.signup(signUpDto);
        if (signupSuccessful) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(summary = "메인 홈화면 상단 회원 정보 api")
    @GetMapping("/loginInfo")
    public ResponseEntity<LoginInfoDto> loginInfo(LoginInfoDto loginInfoDto) {
            return ResponseEntity.ok(loginInfoDto);
    }


    @Operation(summary = "마이페이지 조회 api")
    @GetMapping("/{loginId}/mypage")
    public ResponseEntity<MyPageDto> myPage(@PathVariable("loginId") String loginId) {
        Optional<MyPageDto> myPageDtoOptional = memberService.getMyPageInfo(loginId);

        return myPageDtoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}







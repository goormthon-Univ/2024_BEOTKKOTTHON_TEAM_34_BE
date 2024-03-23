package com.groom.Kkri.controller;

import com.groom.Kkri.dto.BaseResponse;
import com.groom.Kkri.dto.member.HomeUserInfoDto;
import com.groom.Kkri.dto.member.MyPageDto;
import com.groom.Kkri.dto.member.SignUpDto;
import com.groom.Kkri.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Long loginSuccessful = memberService.login(username, password);
        if (loginSuccessful != null) {
            return BaseResponse.response(loginSuccessful);
        } else {
            return BaseResponse.response(-1);
        }
    }

    @Operation(summary = "아이디 중복 체크 api")
    @GetMapping("/idCheck")
    public BaseResponse<String> check(@RequestParam("username") String username) {
        boolean existsByUsername = memberService.existsByUsername(username);
        if (existsByUsername) {
            return BaseResponse.response("fail");
        } else {
            return BaseResponse.response("success");
        }
    }

    @Operation(summary = "닉네임 중복 체크 api")
    @GetMapping("/nickCheck")
    public BaseResponse<String> nickCheck(@RequestParam("nickname") String nickname) {
        boolean existsByNickname = memberService.existsByNickname(nickname);
        if (existsByNickname) {
            return BaseResponse.response("fail");
        } else {
            return BaseResponse.response("success");
        }
    }

    @Operation(summary = "회원가입 api", description = "")
    @PostMapping("/join")
    public BaseResponse<?> signup(@RequestBody SignUpDto signUpDto) {
        boolean signupSuccessful = memberService.signup(signUpDto);
        if (signupSuccessful) {
            return BaseResponse.response("success");
        } else {
            return BaseResponse.response("fail");
        }
    }

    @Operation(summary = "메인 홈화면 상단 회원 정보 api")
    @GetMapping("/loginInfo")
    public BaseResponse<HomeUserInfoDto> loginInfo(@RequestParam("userId") Long userId) {
        HomeUserInfoDto homeInfo = memberService.getHomeInfo(userId);
        return BaseResponse.response(homeInfo);
    }

    @Operation(summary = "마이페이지 조회 api")
    @GetMapping("/{username}/mypage")
    public BaseResponse<MyPageDto> myPage(@PathVariable("username") String username) {
        Optional<MyPageDto> myPageDtoOptional = memberService.getMyPageInfo(username);

        return myPageDtoOptional.map(BaseResponse::response)
                .orElse(new BaseResponse<>(HttpStatus.NOT_FOUND, "마이페이지를 찾을 수 없습니다.", null));
    }

}

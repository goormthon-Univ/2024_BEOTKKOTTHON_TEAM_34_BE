package com.groom.Kkri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean loginSuccessful = memberService.login(username, password);
        if (loginSuccessful) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody Member member) {
        boolean signupSuccessful = memberService.signup(member);
        if (signupSuccessful) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{loginId}/mypage")
    public ResponseEntity<Member> myPage(@PathVariable("loginId") String loginId) {
        Member member = memberService.getMember(loginId);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{loginId}/board/{type}/boardno/{boardId}")
    public ResponseEntity<String> getMyBoard(@PathVariable("loginId") String loginId,
                                             @PathVariable("type") String type,
                                             @PathVariable("boardId") String boardId) {
        // Logic to fetch member's board based on type and boardId
        String boardContent = memberService.getBoardContent(loginId, type, boardId);
        if (boardContent != null) {
            return ResponseEntity.ok(boardContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

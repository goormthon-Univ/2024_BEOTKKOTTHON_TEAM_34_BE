package com.groom.Kkri.service;

import com.groom.Kkri.dto.member.HomeUserInfoDto;
import com.groom.Kkri.dto.member.MemberDto;
import com.groom.Kkri.dto.member.MyPageDto;
import com.groom.Kkri.dto.member.SignUpDto;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findByUsernameAndPassword(String username, String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    public boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean existsByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public Long login(String username, String password) {
        Optional<Member> optionalMember = memberRepository.findByUsernameAndPassword(username, password);
        return optionalMember.map(Member::getId).orElse(null);
    }


    @Transactional
    public boolean signup(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername()) || memberRepository.existsByNickname(signUpDto.getNickname())) {
            return false; // 이미 존재하는 회원이므로 가입 실패
        }

        // 존재하지 않는 경우 회원 정보를 저장
        Member member = Member.builder()
                .nickname(signUpDto.getNickname())
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build();
        memberRepository.save(member);

        return true; // 회원가입 성공
    }
    public HomeUserInfoDto getHomeInfo(Long userId){
        Member member = memberRepository.findById(userId).get();
        return new HomeUserInfoDto(member);
    }


    public Optional<MyPageDto> getMyPageInfo(String username) {
        // loginId를 사용하여 해당 사용자의 정보를 데이터베이스에서 조회
        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        // 사용자 정보가 존재할 경우 MyPageDto로 변환하여 Optional로 반환
        return memberOptional.map(member -> MyPageDto.builder()
                .nickname(member.getNickname())
                .univ(member.getUniv())
                .point(member.getPoint())
                .consumePoint(member.getConsumePoint())
                .earnPoint(member.getEarnPoint())
                .build());
    }

}

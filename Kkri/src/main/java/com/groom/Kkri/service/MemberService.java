package com.groom.Kkri.service;

import com.groom.Kkri.entity.Member;
import com.groom.Kkri.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean existsByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public boolean login(String username, String password) {
        Optional<Member> optionalMember = findByUsernameAndPassword(username, password);
        return optionalMember.isPresent();
    }

    public boolean signup(Member member) {
        if (memberRepository.existsByUsername(member.getUsername()) || memberRepository.existsByNickname(member.getNickname())) {
            return false;
        }

        memberRepository.save(member);
        return true;
    }


    public Member getMember(String loginId) {
        return null;
    }

    public String getBoardContent(String loginId, String type, String boardId) {
        return loginId;
    }
}

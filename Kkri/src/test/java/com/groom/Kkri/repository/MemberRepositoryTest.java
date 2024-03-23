package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    void test(){
        Member member1 = Member.builder()
                .univ("univ")
                .point(3L)
                .nickname("minam")
                .password("1234")
                .username("sim")
                .build();

        Member member2 = Member.builder()
                .univ("univ")
                .point(3L)
                .nickname("gosu")
                .password("2345")
                .username("jae")
                .build();

        Member member3 = Member.builder()
                .univ("univ")
                .point(3L)
                .nickname("note")
                .password("3456")
                .username("jaejae")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        System.out.println(memberRepository.count());
        for(var s : memberRepository.findAll()){
            System.out.println(s.toString());
        }

        Optional<Member> sim = memberRepository.findByUsernameAndPassword("sim", "1234");
        org.assertj.core.api.Assertions.assertThat(sim.get().toString()).isEqualTo(member1.toString());

        Optional<Member> sim1 = memberRepository.findByUsername("jae");
        org.assertj.core.api.Assertions.assertThat(sim1.get().toString()).isEqualTo(member2.toString());

        Boolean note = memberRepository.existsByNickname("note");
        org.assertj.core.api.Assertions.assertThat(note).isTrue();

        Boolean sim2 = memberRepository.existsByUsername("jaejae");
        org.assertj.core.api.Assertions.assertThat(sim2).isTrue();

    }
}
package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsernameAndPassword(String username,String password);

    Optional<Member> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByNickname(String nickname);

}

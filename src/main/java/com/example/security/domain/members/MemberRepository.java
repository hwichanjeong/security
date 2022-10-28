package com.example.security.domain.members;

import com.example.security.domain.members.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    @Override
    Optional<Member> findById(Long aLong);

    @Override
    List<Member> findAll();
}


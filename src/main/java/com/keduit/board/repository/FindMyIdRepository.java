package com.keduit.board.repository;

import com.keduit.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FindMyIdRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail (String email);
}

package com.example.kakao.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}

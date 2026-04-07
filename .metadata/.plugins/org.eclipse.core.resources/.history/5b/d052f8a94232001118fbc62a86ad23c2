package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
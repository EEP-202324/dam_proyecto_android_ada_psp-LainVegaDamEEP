package com.teetech.teetech.repository;

import com.teetech.teetech.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.juba.spring_boot_library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juba.spring_boot_library.model.User;

@Repository
public interface UserInfoRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

package com.juba.spring_boot_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juba.spring_boot_library.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

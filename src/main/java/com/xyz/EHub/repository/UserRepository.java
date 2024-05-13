package com.xyz.EHub.repository;

import com.xyz.EHub.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    public Optional<Users> findByEmail(String email);
    public Optional<Users> findByName(String email);
}

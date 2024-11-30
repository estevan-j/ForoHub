package com.ForoHub.ForoAPI.repositories;

import com.ForoHub.ForoAPI.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}

package com.ditra.travelagency.core.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositroy extends JpaRepository<User, Integer> {
    Optional<User> findByUsername (String username);
}

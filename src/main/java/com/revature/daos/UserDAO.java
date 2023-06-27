package com.revature.daos;

import com.revature.models.Status;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findByNameContainingIgnoreCase(String pattern);

    boolean existsByUsername(String username);
}

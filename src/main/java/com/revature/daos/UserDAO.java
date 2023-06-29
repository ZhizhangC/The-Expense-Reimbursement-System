package com.revature.daos;

import com.revature.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByUsername(String username);

    @Query("FROM MyUser WHERE firstname LIKE %:pattern% OR lastname LIKE %:pattern% ")
    List<MyUser> findByNameContainingIgnoreCase(String pattern);

    boolean existsByUsername(String username);
}

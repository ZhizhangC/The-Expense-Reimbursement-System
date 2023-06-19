package com.revature.daos;

import com.revature.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findByTitle(String title);

    List<Role> findByTitleContainingIgnoreCase(String pattern);

    @Query("FROM Role WHERE title LIKE %:pattern% ") // JPQL not Native SQL
    List<Role> findByTitleSearch(@Param("pattern") String pattern);
}
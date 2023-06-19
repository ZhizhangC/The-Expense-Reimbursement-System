package com.revature.daos;

import com.revature.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatusDAO extends JpaRepository<Status, Integer> {
    Status findByName(String name);

    List<Status> findByNameContainingIgnoreCase(String pattern);

    @Query("FROM Status WHERE name LIKE %:pattern% ") // JPQL not Native SQL
    List<Status> findByNameSearch(@Param("pattern") String pattern);
}

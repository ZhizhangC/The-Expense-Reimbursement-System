package com.revature.daos;
import com.revature.models.MyUser;
import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {
//    @Query("FROM Reimbursement WHERE LOWER(description) LIKE (%:pattern%) ")
//    List<Reimbursement> findByDescriptionContainingIgnoreCase(String pattern);

//    @Query("SELECT r FROM Reimbursement r WHERE r.user_id = :id")
//    List<Reimbursement> getReimbursementsByUser(@Param("id") int id);



//    @Query("SELECT r.* FROM Reimbursement r JOIN Status s ON r.status_id = s.id WHERE r.user_id = MyUser.:id AND s.name = 'Pending'")
    @Query("FROM Reimbursement r WHERE r.status_id = 3 AND r.user_id = :id")
    List<Reimbursement> getPendingReimbursementsByUser(@Param("id") MyUser id);

//    @Query("SELECT r.* FROM Reimbursement r JOIN Status s ON r.status_id = s.id WHERE r.user_id = MyUser.:id AND s.name = 'Denied' OR s.name = 'Approved'")
    @Query("FROM Reimbursement r WHERE (r.status_id = 1 OR r.status_id = 2) AND r.user_id = :id")
    List<Reimbursement> getResolvedReimbursementsByUser(@Param("id") MyUser id);


    @Query("FROM Reimbursement r WHERE r.status_id = 3")
    List<Reimbursement> getAllPendingReimbursements();

    @Query("FROM Reimbursement r WHERE r.status_id = 1 OR r.status_id = 2")
    List<Reimbursement> getAllResolvedReimbursements();

    @Query("UPDATE Reimbursement r SET r.status_id = 1 WHERE r.id = :id")
    boolean approveReimbursement(@Param("id") int id);

    @Query("UPDATE Reimbursement r SET r.status_id = 2 WHERE r.id = :id")
    boolean denyReimbursement(@Param("id") int id);

}

package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.RoleDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.UserDAO;
import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;
    private final StatusDAO statusDAO;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO, StatusDAO statusDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.statusDAO = statusDAO;
    }

    public Reimbursement createReimbursement(Reimbursement r){
        Reimbursement returnedReimbursement = reimbursementDAO.save(r);
        if(returnedReimbursement.getId()>0){
            log.info("Reimbursement created");
        }else{
            log.warn("Failed to create Reimbursement");
        }
        return returnedReimbursement;
    }

    public List<Reimbursement> getAllReimbursement(){
        return reimbursementDAO.findAll();
    }

    public Reimbursement getReimbursementById(int id){
        return reimbursementDAO.findById(id).orElseThrow(() -> new ReimbursementNotFoundException("No reimbursement found with id: " + id));
    }

    public Reimbursement updateReimbursement(Reimbursement r){
        return reimbursementDAO.save(r);
    }

    public boolean deleteReimbursementById(int id){
        reimbursementDAO.deleteById(id);
        if (!reimbursementDAO.existsById(id)){
            return true;
        } else{
            return false;
        }
    }

    public List<Reimbursement> searchReimbursements(String searchPattern) {
        return reimbursementDAO.findByNameContainingIgnoreCase(searchPattern);
    }

    public List<Reimbursement> getReimbursementsByPerson(int pid) {
        return reimbursementDAO.getReimbursementsByUser(pid);
    }

    public List<Reimbursement> getAllPendingReimbursements() {
        return reimbursementDAO.getAllPendingReimbursements();
    }

    public boolean approveReimbursement(int id) {
        Optional<Reimbursement> optionalReimbursement = reimbursementDAO.findById(id);
        Status status = statusDAO.getReferenceById(2);
        if (optionalReimbursement.isPresent()) {
            Reimbursement reimbursement = optionalReimbursement.get();
            System.out.println(reimbursement);
            reimbursement.setStatus_id(status); // Set the new status value
            reimbursementDAO.save(reimbursement); // Save the modified reimbursement object
        }
        return true;
    }

    public boolean denyReimbursement(int id) {
        Optional<Reimbursement> optionalReimbursement = reimbursementDAO.findById(id);
        Status status = statusDAO.getReferenceById(3);
        if (optionalReimbursement.isPresent()) {
            Reimbursement reimbursement = optionalReimbursement.get();
            System.out.println(reimbursement);
            reimbursement.setStatus_id(status); // Set the new status value
            reimbursementDAO.save(reimbursement); // Save the modified reimbursement object
        }
        return true;
    }
}

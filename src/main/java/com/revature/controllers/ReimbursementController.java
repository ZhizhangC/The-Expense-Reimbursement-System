package com.revature.controllers;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reimbursements")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ReimbursementController {
    private final ReimbursementService reimbursementService;
    private final StatusDAO statusDAO;
    private final ReimbursementDAO reimbursementDAO;
    private final UserDAO userDAO;
    private final UserService userService;

//    private final JwtGenerator jwtGenerator;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService, StatusDAO statusDAO, ReimbursementDAO reimbursementDAO, UserDAO userDAO, UserService userService) {
        this.reimbursementService = reimbursementService;
        this.statusDAO = statusDAO;
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
        this.userService = userService;
//        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/pending/{id}")
    public List<Reimbursement> getPendingReimbursementsByPersonHandler(@PathVariable("id") int id) {
        return reimbursementService.getPendingReimbursementsByPerson(id);
    }

    @GetMapping("/resolved/{id}")
    public List<Reimbursement> getResolvedReimbursementsByPersonHandler(@PathVariable("id") int id) {
        return reimbursementService.getResolvedReimbursementsByPerson(id);
    }
//
    @GetMapping
    public List<Reimbursement> getAllReimbursementsHandler() {
        return reimbursementService.getAllReimbursement();
    }

    @GetMapping("/users/pending")
    public List<Reimbursement> getAllPendingReimbursementsHandler() {
        return reimbursementService.getAllPendingReimbursements();
    }

    @GetMapping("/users/resolved")
    public List<Reimbursement> getAllResolvedReimbursementsHandler() {
        return reimbursementService.getAllResolvedReimbursements();
    }

////    @GetMapping("/pending")
////    public List<Reimbursement> getAllPendingReimbursementsHandler() {
////        return reimbursementService.getAllPendingReimbursements();
////    }
//
//    @GetMapping("{id}")
//    public Reimbursement findReimbursementByIdHandler(@PathVariable("id") int id) {
//        return reimbursementService.getReimbursementById(id);
//    }
//
////    @GetMapping("/user/{id}")
////    public List<Reimbursement> getReimbursementsByPersonId(@PathVariable("id") int id) {
////        return reimbursementService.getReimbursementsByPerson(id);
////    }

    @PostMapping("/create/{id}")
    public void createReimbursementByUserHandler(@RequestParam int a, @RequestParam String s, @PathVariable("id") int id){
        reimbursementService.createReimbursementByUser(a, s, id);
    }
//
//    @PostMapping
//    public Reimbursement createReimbursementHandler(@RequestBody Reimbursement r, @RequestHeader("Authorization") String bearerToken){
//        Status status = statusDAO.findByName("Pending");
//
//        String username = jwtGenerator.getUsernameFromToken(bearerToken.substring(7));
//        MyUser u = userService.findUserByUsername(username);
//
//        r.setStatus_id(status);
//        r.setUser_id(u);
//        return reimbursementService.createReimbursement(r);
//    }
//
//    @PutMapping
//    public Reimbursement updateReimbursementHandler(@RequestBody Reimbursement r) {
//        return reimbursementService.updateReimbursement(r);
//    }
//
    @PutMapping("/approve/{id}")
    public boolean approveReimbursementHandler(@PathVariable("id") int id) {
        return reimbursementService.approveReimbursement(id);
    }
//
    @PutMapping("/deny/{id}")
    public boolean denyReimbursementHandler(@PathVariable("id") int id) {
        return reimbursementService.denyReimbursement(id);
    }
//
//    @DeleteMapping("{id}")
//    public boolean deleteReimbursementHandler(@PathVariable("id") int id) {
//        return reimbursementService.deleteReimbursementById(id);
//    }
}

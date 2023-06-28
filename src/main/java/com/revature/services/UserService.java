package com.revature.services;

//import com.revature.daos.ReimbursementDAO;
import com.revature.daos.RoleDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.UserDAO;
import com.revature.exceptions.UserNotFoundException;
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
public class UserService {
    private final UserDAO userDao;
    private final RoleDAO roleDao;
//    private final ReimbursementDAO reimbursementDAO;
    private final StatusDAO statusDAO;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService(UserDAO userDao, RoleDAO roleDao, StatusDAO statusDAO) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.statusDAO = statusDAO;
    }
//    public UserService(UserDAO userDao, RoleDAO roleDao, ReimbursementDAO reimbursementDAO, StatusDAO statusDAO) {
//        this.userDao = userDao;
//        this.roleDao = roleDao;
//        this.reimbursementDAO = reimbursementDAO;
//        this.statusDAO = statusDAO;
//    }

    public void createUser(User u){
        if(userDao.existsByUsername(u.getUsername()) == false){
            User returnedUser = userDao.save(u);
            log.info("User created");
        }else{
            log.warn("Failed to create user");
        }
    }

    public List<User> getAllUser(){
        return userDao.findAll();
    }


    public User getUserById(int id){
        return userDao.findById(id).orElseThrow(() -> new UserNotFoundException("No user found with id: " + id));
    }

    public User findUserByUsername(String username){
        return userDao.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No user found with name: " + username));
    }

    public User updateUser(User u){
        return userDao.save(u);
    }

    public List<User> searchUser(String searchPattern){
        return userDao.findByNameContainingIgnoreCase(searchPattern);
    }

    public boolean deleteUserById(int id){
        userDao.deleteById(id);
        if (!userDao.existsById(id)){
            // Successful message
            return true;
        } else{
            return false;
        }
    }
//
//    public Role getRoleByUserId(int id){
//        Optional<User> returnedUser = userDao.findById(id);
//
//        if (returnedUser.isPresent()){
//            return returnedUser.get().getRole_id_fk();
//        } else{
//            throw new UserNotFoundException("No User with id: " + id);
//        }
//    }
}

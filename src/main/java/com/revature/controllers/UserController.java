package com.revature.controllers;

import com.revature.models.MyUser;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<MyUser> getAllUserHandler(@RequestParam(name = "search", required = false) String searchPattern){

        if (searchPattern != null){
            return userService.searchUser(searchPattern);
        }

        return userService.getAllUser();
    }

    @GetMapping("{id}") // /users/id
    public MyUser getUserByIdHandler(@PathVariable("id") int id){

        return userService.getUserById(id);
    }

    @GetMapping("{username}") // /users/id
    public MyUser getUserByUsernameHandler(@PathVariable("username") String u){

        return userService.findUserByUsername(u);
    }

    @PostMapping
    public void createUserHandler(@RequestBody MyUser u){
        userService.createUser(u);
    }

    @PutMapping("{id}")
    public MyUser updateUserHandler(@RequestBody MyUser u){

        return userService.updateUser(u);
    }

    @DeleteMapping("{id}")
    public boolean deleteUserHandler(@PathVariable("id") int id){

        return userService.deleteUserById(id);
    }

}

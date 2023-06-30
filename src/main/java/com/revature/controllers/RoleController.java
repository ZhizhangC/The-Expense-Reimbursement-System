package com.revature.controllers;

import com.revature.models.Role;
import com.revature.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRolesHandler(@RequestParam(name = "search", required = false) String searchPattern){

        if (searchPattern != null){
            return roleService.searchRoles(searchPattern);
        }

        return roleService.getAllRoles();
    }

    @GetMapping("{id}")
    public Role findRoleByIdHandler(@PathVariable("id") int id){
        return roleService.findRoleById(id);
    }

    @PostMapping
    public Role createRoleHandler(@RequestBody Role r){
        return roleService.addRole(r);
    }

    @PutMapping
    public Role updateRoleHandler(@RequestBody Role r){
        return roleService.updateRole(r);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteRoleHandler(@PathVariable("id") int id){
        return roleService.deleteRole(id);
    }
}

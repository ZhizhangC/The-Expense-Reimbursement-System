package com.revature.services;

import com.revature.daos.RoleDAO;
import com.revature.exceptions.RoleNotFoundException;
import com.revature.exceptions.StatusNotFoundException;
import com.revature.models.Role;
import com.revature.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleDAO roleDao;

    @Autowired
    public RoleService(RoleDAO roleDao){
        this.roleDao = roleDao;
    }

    public Role addRole(Role role){

        Role returnedRole = roleDao.save(role);

        if (returnedRole.getId() > 0){
            // Successful
            // TODO put success log
        } else{
            // This was a failure to add the course
            // TODO put warning log
        }

        return returnedRole;

    }

    public Role updateRole(Role r){
        return roleDao.save(r);
    }

    public boolean deleteRole(int id){
        roleDao.deleteById(id);

        return !(roleDao.existsById(id));
    }

    public Role findRoleById(int id){
        return roleDao.findById(id).orElseThrow(() -> new RoleNotFoundException("No Role found with id: " + id));
    }

    public List<Role> getAllRoles(){
        List<Role> role = roleDao.findAll();

        return role;
    }

    public List<Role> searchRoles(String searchPattern){
        return roleDao.findByTitleContainingIgnoreCase(searchPattern);
    }
}

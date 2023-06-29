package com.revature.security;

import com.revature.daos.UserDAO;
import com.revature.models.Role;
import com.revature.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDao;

    @Autowired
    public CustomUserDetailsService(UserDAO userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser u = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No MyUser Found"));
        return new User(u.getUsername(), u.getPassword(), mapRoleToAuthority(u.getRole_id_fk()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getTitle()));

        return grantedAuthorities;

    }
}

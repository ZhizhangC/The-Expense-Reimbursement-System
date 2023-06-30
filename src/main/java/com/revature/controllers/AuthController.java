package com.revature.controllers;


import com.revature.daos.RoleDAO;
import com.revature.daos.UserDAO;
import com.revature.dto.AuthResponseDTO;
import com.revature.dto.LoginDTO;
import com.revature.dto.RegisterDTO;
import com.revature.models.MyUser;
import com.revature.models.Role;
import com.revature.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDAO userDao;
    private final RoleDAO roleDao;
    private final PasswordEncoder passwordEncoder;

    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDAO userDao, RoleDAO roleDao,
                          PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){


        if (userDao.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<String>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        MyUser p = new MyUser();
        p.setFirstName(registerDTO.getFirstName());
        p.setLastName(registerDTO.getLastName());
        p.setUsername(registerDTO.getUsername());
        p.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role role = roleDao.findByTitle("Employee");

        p.setRole_id_fk(role);

        // We've built the proper person object so let's save it now
        userDao.save(p);

        return new ResponseEntity<>("User successfully registered!", HttpStatus.CREATED);

    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<AuthResponseDTO>(new AuthResponseDTO(token), HttpStatus.OK);
    }


}

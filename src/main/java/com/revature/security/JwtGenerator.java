package com.revature.security;

import com.revature.models.MyUser;
import com.revature.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenerator {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Autowired
    private UserService userService;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));
        MyUser myUser = userService.findUserByUsername(username);
        String token = Jwts.builder()
                .setSubject(username)
                .claim("Id", myUser.getId())
                .claim("Role", myUser.getRole_id_fk().getTitle())
                .claim("FirstName", myUser.getFirstName())
                .claim("LastName", myUser.getLastName())
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)// We need a secret key which SHOULD NOT be shared, otherwise bad
                .compact();

        return token;
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT token is expired or invalid");
        }
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}

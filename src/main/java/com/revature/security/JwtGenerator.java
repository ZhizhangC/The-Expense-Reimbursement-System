package com.revature.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenerator {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

//    public String generateToken(Authentication authentication) {
//        String username = authentication.getName();
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(currentDate)
//                .setExpiration(expireDate)
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//
//        return token;
//    }
}

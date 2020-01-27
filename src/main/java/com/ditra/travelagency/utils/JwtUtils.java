package com.ditra.travelagency.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private final String SECRET = "JWTSECRET";

    public String genratedtoken (String username){

        return  Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, SECRET )
                .compact();
    }
    public String verifyandgteusername (String token){

        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception exception){
            return null;
        }



    }



}

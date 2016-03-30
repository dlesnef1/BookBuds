package com.ithaca.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 3/29/16.
 */
@Service
public class UserHelper {

    public Map<String, String> generateToken(User user) {

        // TODO add time it expires
        // TODO store secret in better way

        String token =  Jwts.builder()
                .setSubject(user.getName())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String > tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return tokenMap;
    }
}

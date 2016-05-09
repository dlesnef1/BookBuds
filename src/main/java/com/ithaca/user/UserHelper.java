package com.ithaca.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class that can generate a token based off of a user. This token then identifies the user for future backend hits.
 */
@Service
public class UserHelper {

    public Map<String, String> generateToken(User user) {

        String token =  Jwts.builder()
                .setSubject(user.getName())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String > tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return tokenMap;
    }
}

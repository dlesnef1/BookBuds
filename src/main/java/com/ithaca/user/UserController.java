package com.ithaca.user;

import com.ithaca.BookBudsApplication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 3/24/2016.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping
    public List<User> all() {
        return userService.all();
    }

    @RequestMapping("/account")
    public User find(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        return userService.find((long) claims.get("id"));
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(String name, String password) {
        //TODO log in afterwards

        return userService.create(name, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestParam(value = "name") String name,
                                     @RequestParam(value = "password") String password) {
        User user = userService.checkValid(name, password);
        if (user == null) {
            return null;
        }

        // TODO store secret in better way
        String token = Jwts.builder()
                .setSubject(name)
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret").compact();

        Map <String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return tokenMap;
    }
}

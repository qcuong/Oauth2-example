package com.quoccuong.oauth2example.controller;

import com.quoccuong.oauth2example.config.service.UserSerice;
import com.quoccuong.oauth2example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author QUOCCUONG
 * <p>
 * 1/27/2019
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserSerice userSerice;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userSerice.create(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sign-out")
    @ResponseBody
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(tokenId);
        }
    }
}

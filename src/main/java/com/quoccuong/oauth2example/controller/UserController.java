package com.quoccuong.oauth2example.controller;

import com.quoccuong.oauth2example.config.service.UserSerice;
import com.quoccuong.oauth2example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author QUOCCUONG
 * <p>
 * 1/26/2019
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserSerice userSerice;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userSerice.findAll();
    }


}

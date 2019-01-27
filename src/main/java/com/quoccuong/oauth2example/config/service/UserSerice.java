package com.quoccuong.oauth2example.config.service;

import com.quoccuong.oauth2example.config.repository.UserRepository;
import com.quoccuong.oauth2example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author QUOCCUONG
 * <p>
 * 1/26/2019
 */

@Service(value = "userService")
public class UserSerice implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new
                org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getDefaultAuthority());
    }

    private List getDefaultAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user){
        return userRepository.save(user);
    }
}

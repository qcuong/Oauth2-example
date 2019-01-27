package com.quoccuong.oauth2example.config.repository;

import com.quoccuong.oauth2example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author QUOCCUONG
 * <p>
 * 1/26/2019
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}

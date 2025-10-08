package org.team1.team1shoppingcart.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.team1.team1shoppingcart.model.entity.User;
import org.team1.team1shoppingcart.model.repository.UserRepository;
import org.team1.team1shoppingcart.model.req.LoginReq;
import org.team1.team1shoppingcart.model.req.RegisterReq;

import java.util.Map;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 01:04
 * @Param
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    public User login(LoginReq req) {
        User user = userRepository.findByUsername(req.getUsername());
        if (user == null) {
            log.info("User not found: {}", req.getUsername());
            return null;
        }
        if (!user.getPassword().equals(req.getPassword())) {
            log.info("Wrong password: {}", req.getPassword());
            return null;
        }
        return user;
    }

    public User register(RegisterReq req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            log.info("Username already in use: {}", req.getUsername());
            return null;
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            log.info("Email already in use: {}", req.getEmail());
            return null;
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setFullName(req.getFullName());
        user.setPhone(req.getPhone());
        user.setAddress(req.getAddress());
        user.setRole("USER");
        user.setActive(true);

        userRepository.save(user);
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.info("User not found: {}", username);
            return null;
        }
        return user;
    }
}

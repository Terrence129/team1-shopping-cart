package org.team1.team1shoppingcart.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team1.team1shoppingcart.model.entity.User;
import org.team1.team1shoppingcart.model.req.LoginReq;
import org.team1.team1shoppingcart.model.req.RegisterReq;
import org.team1.team1shoppingcart.service.AuthService;

import java.util.Map;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 00:52
 * @Param
 **/

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReq req, HttpSession session) {
        User user = authService.login(req);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            return ResponseEntity.ok(Map.of("success", true, "username", user.getUsername()));
        }else {
            return ResponseEntity.status(401).body(Map.of("success", false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReq req) {
        User user = authService.register(req);
        if (user != null) {
            return ResponseEntity.ok(Map.of("success", true));
        }else {
            return ResponseEntity.status(401).body(Map.of("success", false));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("success", true));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(Map.of("username", user));
        }
        return ResponseEntity.status(401).body(Map.of("success", false));
    }
}

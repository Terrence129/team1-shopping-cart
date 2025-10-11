package org.team1.team1shoppingcart.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team1.team1shoppingcart.exception.UnauthorizedException;
import org.team1.team1shoppingcart.model.req.CheckoutReq;
import org.team1.team1shoppingcart.model.resp.CartResp;
import org.team1.team1shoppingcart.service.CartService;

import java.util.Map;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 01:56
 * @Param
 **/

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResp> getCart(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        return ResponseEntity.ok(cartService.getCart(username));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(HttpSession session, @RequestParam Long productId, @RequestParam Integer quantity) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        cartService.addItemToCart(username, productId, quantity);
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeItemFromCart(HttpSession session, @RequestParam Long productId, @RequestParam Integer quantity) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        cartService.removeItemFromCart(username, productId, quantity);
        return ResponseEntity.ok(Map.of("success", true));
    }


    @DeleteMapping("/empty")
    public ResponseEntity<?> emptyCart(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        cartService.emptyCart(username);
        return null;
    }


}

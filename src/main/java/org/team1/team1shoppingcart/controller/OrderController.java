package org.team1.team1shoppingcart.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team1.team1shoppingcart.exception.UnauthorizedException;
import org.team1.team1shoppingcart.model.entity.Order;
import org.team1.team1shoppingcart.model.req.CheckoutReq;
import org.team1.team1shoppingcart.service.OrderService;

import java.util.Map;

/**
 * @author chenyaqi
 * @Description
 * @Date 7/10/25 01:27
 * @Param
 **/

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(HttpSession session, @RequestBody CheckoutReq req) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        return ResponseEntity.ok(Map.of("success", true, "orderId", orderService.checkout(username, req)));
    }

    @GetMapping("")
    public ResponseEntity<?> getOrders(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        return ResponseEntity.ok(orderService.getOrders(username));
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrder(HttpSession session, @PathVariable("order_id") Long order_id) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        return ResponseEntity.ok(orderService.getOrder(order_id));
    }

    @PostMapping("/{order_id}/pay")
    public ResponseEntity<?> payOrder(HttpSession session, @PathVariable("order_id") Long order_id) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new UnauthorizedException("not logged in");
        }
        orderService.payOrder(order_id);
        return ResponseEntity.ok(Map.of("success", true));
    }

}

package org.team1.team1shoppingcart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.team1.team1shoppingcart.exception.BusinessException;
import org.team1.team1shoppingcart.model.entity.*;
import org.team1.team1shoppingcart.model.repository.*;
import org.team1.team1shoppingcart.model.req.CheckoutReq;
import org.team1.team1shoppingcart.model.resp.OrderResp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 7/10/25 01:27
 * @Param
 **/

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long checkout(String username, CheckoutReq req){


        // find all cartItems
        List<CartItem> cartItems = cartItemRepository.findAllByIdWithProduct(req.getCartItemIds());

        // check stock
        cartItems.forEach(c -> {
            if(c.getProduct().getStock() < c.getQuantity()){
                throw new BusinessException("Not enough stock");
            }
        });

        // generate corresponding order and orderItems
        // generate order
        Order order = new Order();
        order.setOrderNumber(username + Instant.now().getEpochSecond()); // test
        order.setUser(userRepository.findByUsername(username));
        order.setStatus("PENDING");
        order.setTotalAmount(cartItems.stream().map(i ->
            i.getUnitPrice()
                .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setTotalQuantity(cartItems.stream().map(CartItem::getQuantity).reduce(Integer::sum).get());
        order.setPaymentMethod(req.getPaymentMethod());
        order.setRecipientName(req.getRecipientName());
        order.setRecipientPhone(req.getRecipientPhone());
        order.setShippingAddr(req.getShippingAddr());
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setNotes(req.getNotes());
        Long orderId = orderRepository.save(order).getId();

        // generate orderItems
        List<OrderItem> orderItems = cartItems.stream()
                .map(a -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(a.getProduct());
                    orderItem.setUnitPrice(a.getUnitPrice());
                    orderItem.setQuantity(a.getQuantity());
                    orderItem.setCreatedAt(Instant.now());
                    orderItem.setUpdatedAt(Instant.now());
                    return orderItem;
                })
                .toList();
        orderItemRepository.saveAll(orderItems);


        cartItems.forEach(cartItem -> {
            // delete these cartItems from cart
            cartItemRepository.removeCartItemById(cartItem.getId());
            // decrease product stock
            productRepository.decreaseStock(cartItem.getProduct().getId(), cartItem.getQuantity());
        });


        return orderId;
    }

    public List<OrderResp> getOrders(String username){
        User user = userRepository.findByUsername(username);
        List<Order> orders = orderRepository.findOrdersByUserId(user.getId());
        List<OrderResp> orderResps = orders.stream().map(o -> getOrder(o.getId())).toList();
        return orderResps;
    }

    public OrderResp getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        OrderResp orderResp = new OrderResp();
        orderResp.setOrderId(orderId);
        orderResp.setOrderNumber(order.getOrderNumber());
        orderResp.setUserId(order.getUser().getId());
        orderResp.setStatus(order.getStatus());
        orderResp.setPaymentMethod(order.getPaymentMethod());
        orderResp.setRecipientName(order.getRecipientName());
        orderResp.setRecipientPhone(order.getRecipientPhone());
        orderResp.setShippingAddr(order.getShippingAddr());
        orderResp.setNotes(order.getNotes());
        orderResp.setCreatedAt(order.getCreatedAt());
        orderResp.setUpdatedAt(order.getUpdatedAt());
        orderResp.setCancelledAt(order.getCancelledAt());
        orderResp.setDeliveredAt(order.getDeliveredAt());
        orderResp.setPaidAt(order.getPaidAt());
        orderResp.setTotalPrice(order.getTotalAmount());
        orderResp.setTotalQuantity(order.getTotalQuantity());
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        List<OrderResp.Item> orderRespItems = orderItems.stream().map(
                i -> {
                    OrderResp.Item item = new OrderResp.Item();
                    item.setProductId(i.getId());
                    item.setProductName(i.getProduct().getName());
                    item.setImageUrl(i.getProduct().getImageUrl());
                    item.setQuantity(i.getQuantity());
                    item.setUnitPrice(i.getUnitPrice());
                    item.setSubtotal(i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())));
                    return item;
                }
        ).toList();
        orderResp.setItems(orderRespItems);
        return orderResp;
    }

    public void payOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        order.setStatus("PAID");
        order.setPaidAt(Instant.now());
        orderRepository.save(order);
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }

    public void deliverOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        order.setStatus("DELIVERED");
    }
}

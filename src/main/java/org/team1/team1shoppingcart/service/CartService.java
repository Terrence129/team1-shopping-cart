package org.team1.team1shoppingcart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.team1.team1shoppingcart.exception.BusinessException;
import org.team1.team1shoppingcart.model.entity.*;
import org.team1.team1shoppingcart.model.repository.CartItemRepository;
import org.team1.team1shoppingcart.model.repository.CartRepository;
import org.team1.team1shoppingcart.model.repository.ProductRepository;
import org.team1.team1shoppingcart.model.repository.UserRepository;
import org.team1.team1shoppingcart.model.req.CheckoutReq;
import org.team1.team1shoppingcart.model.resp.CartResp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 16:04
 * @Param
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartResp getCart(String username) {

        User currentUser = userRepository.findByUsername(username);

        Cart cart = cartRepository.findCartByUser(currentUser);
        if (cart == null) {
            throw new BusinessException("Cart not found");
        }
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cart.getId());
        List<CartResp.Item> cartRespItems = cartItems.stream().map(
                i -> {
                    CartResp.Item item = new CartResp.Item();
                    item.setCartItemId(i.getId());
                    item.setProductId(i.getProduct().getId());
                    item.setProductName(i.getProduct().getName());
                    item.setImageUrl(i.getProduct().getImageUrl());
                    item.setQuantity(i.getQuantity());
                    item.setUnitPrice(i.getUnitPrice());
                    item.setSubtotal(i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())));
                    return item;
                }
        ).toList();
        CartResp cartResp = new CartResp();
        cartResp.setItems(cartRespItems);
        cartResp.setCartId(cart.getId());
        cartResp.setUserId(currentUser.getId());
        cartResp.setActive(cart.getActive());
        cartResp.setTotalQuantity(cartItems.stream()
                .mapToInt(CartItem::getQuantity).sum());
        cartResp.setTotalPrice(cartItems.stream().map(i ->
                i.getUnitPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return cartResp;
    }

    // create a cart
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setActive(true);
        cart.setCreatedAt(Instant.now());
        cart.setUpdatedAt(Instant.now());
        return cartRepository.save(cart);
    }

    public void addItemToCart(String username, Long productId, Integer quantity) {
        User currentUser = userRepository.findByUsername(username);
        Cart cart = cartRepository.findCartByUser(currentUser);
        // check stock
        if (productRepository.getStock(productId) < quantity){
            throw new BusinessException("Not enough stock");
        }
        if (cart == null) {
            // create a cart
            cart = createCart(currentUser);
        }
        Product product = productRepository.findProductById(productId);
        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartAndProduct(cart, product);
        if (!cartItems.isEmpty()) {
            cartItemRepository.updateCartItemQuantity(cart, product, quantity);
            return;
        }
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUnitPrice(product.getPrice());
        cartItem.setCreatedAt(Instant.now());
        cartItem.setUpdatedAt(Instant.now());
        cartItemRepository.save(cartItem);
    }

    public void removeItemFromCart(String username, Long productId, Integer quantity) {
        User currentUser = userRepository.findByUsername(username);
        Cart cart = cartRepository.findCartByUser(currentUser);
        Product product = productRepository.findProductById(productId);
        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartAndProduct(cart, product);
        if (cartItems.size() < quantity) {
            cartItemRepository.removeCartItemsByCartAndProduct(cart, product);
            return;
        }
        cartItemRepository.updateCartItemQuantity(cart, product, -quantity);

    }
}

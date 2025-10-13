package org.team1.team1shoppingcart.model.repository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.team1.team1shoppingcart.model.entity.Cart;
import org.team1.team1shoppingcart.model.entity.CartItem;
import org.team1.team1shoppingcart.model.entity.Product;

import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:09
 * @Param
 **/
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findCartItemById(Long id);

    @Query("select ci.product from CartItem ci where ci.id in :ids")
    List<Product> findProductsByIds(@Param("ids") List<Long> ids);

    @Query("select ci from CartItem ci join fetch ci.product where ci.id in :ids")
    List<CartItem> findAllByIdWithProduct(@Param("ids") List<Long> ids);

    List<CartItem> findAllByCartId(Long cartId);

    CartItem findCartItemsByCartAndProduct(Cart cart, Product product);
    @Transactional
    @Modifying
    void removeCartItemsByCartAndProduct(Cart cart, Product product);

    @Transactional
    @Modifying
    void removeCartItemById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = c.quantity + :quantity WHERE c.cart = :cart AND c.product = :product")
    void updateCartItemQuantity(@Param("cart") Cart cart, @Param("product") Product product, @Param("quantity") int quantity);

    void removeCartItemsByCart(Cart cart);

    void deleteAllByProduct(@NotNull Product product);
}

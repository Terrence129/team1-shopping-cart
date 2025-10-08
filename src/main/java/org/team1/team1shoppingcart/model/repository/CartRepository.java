package org.team1.team1shoppingcart.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.team1.team1shoppingcart.model.entity.Cart;
import org.team1.team1shoppingcart.model.entity.User;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:07
 * @Param
 **/
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUserId(Long userId);

    Cart findCartByUser(User user);
}

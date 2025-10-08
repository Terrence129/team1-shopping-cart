package org.team1.team1shoppingcart.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.team1.team1shoppingcart.model.entity.Order;

import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:09
 * @Param
 **/
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);
}

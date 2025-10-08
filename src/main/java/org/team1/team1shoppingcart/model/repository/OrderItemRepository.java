package org.team1.team1shoppingcart.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.team1.team1shoppingcart.model.entity.OrderItem;

import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:10
 * @Param
 **/
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrderId(Long id);
}

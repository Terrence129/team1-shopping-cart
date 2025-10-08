package org.team1.team1shoppingcart.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team1.team1shoppingcart.model.entity.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 7/10/25 02:20
 * @Param
 **/
@Data
public class OrderResp {

    private Long orderId;
    private String orderNumber;
    private Long userId;
    private String status;

    private Integer totalQuantity;
    private BigDecimal totalPrice;

    private String paymentMethod;
    private String recipientName;
    private String recipientPhone;
    private String shippingAddr;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant cancelledAt;
    private Instant deliveredAt;

    private String notes;
    private Instant paidAt;

    private List<OrderResp.Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long productId;
        private String productName;
        private String imageUrl;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;
    }
}

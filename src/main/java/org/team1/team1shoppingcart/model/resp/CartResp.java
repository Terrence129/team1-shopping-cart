package org.team1.team1shoppingcart.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 17:16
 * @Param
 **/

@Data
public class CartResp {

    private Long cartId;
    private Long userId;
    private Boolean active;

    private Integer totalQuantity;
    private BigDecimal totalPrice;

    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long cartItemId;
        private Long productId;
        private String productName;
        private String imageUrl;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;
        private Integer stock;
    }
}

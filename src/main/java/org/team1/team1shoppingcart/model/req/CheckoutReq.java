package org.team1.team1shoppingcart.model.req;

import lombok.Data;

import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 7/10/25 00:58
 * @Param
 **/

@Data
public class CheckoutReq {
    private List<Long> cartItemIds;
    private String paymentMethod;
    private String recipientName;
    private String recipientPhone;
    private String shippingAddr;

    private String notes;
}

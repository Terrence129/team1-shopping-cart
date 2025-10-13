package org.team1.team1shoppingcart.model.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenyaqi
 * @Description
 * @Date 12/10/25 22:55
 * @Param
 **/

@Data
public class NewProductReq {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private Boolean visible = true;
    private Boolean active = true;

}

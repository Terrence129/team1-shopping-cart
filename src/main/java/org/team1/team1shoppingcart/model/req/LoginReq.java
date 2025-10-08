package org.team1.team1shoppingcart.model.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 16:11
 * @Param
 **/

@Data
public class LoginReq {

    @NotBlank(message = "username cannot be empty")
    private String username;
    @NotBlank(message = "password cannot be empty")
    private String password;
}

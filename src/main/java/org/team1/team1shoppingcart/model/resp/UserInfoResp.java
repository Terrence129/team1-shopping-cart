package org.team1.team1shoppingcart.model.resp;

import lombok.Data;

import java.time.Instant;

/**
 * @author chenyaqi
 * @Description
 * @Date 12/10/25 17:36
 * @Param
 **/

@Data
public class UserInfoResp {

    private Long id;

    private String username;

    private String fullName;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private Boolean active;

    private String role;

    private Instant createdAt;

    private Instant updatedAt;
}

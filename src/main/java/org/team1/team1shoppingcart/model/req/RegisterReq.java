package org.team1.team1shoppingcart.model.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 00:56
 * @Param
 **/

@Data
public class RegisterReq {

    @NotBlank(message = "username cannot be empty")
    @Size(min = 3, max = 50, message = "length of username should be 3~50")
    private String username;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 6, max = 100, message = "length of password should be at least 6")
    private String password;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email format not correct")
    @Size(max = 100)
    private String email;

    @Size(max = 50, message = "length of first name should not exceeding 50")
    private String firstName;

    @Size(max = 50, message = "length of last name should not exceeding 50")
    private String lastName;

    @Size(max = 20, message = "length of phone number should not exceeding 20")
    private String phone;

    @Size(max = 200, message = "length of address should not exceeding 200")
    private String address;

    @Size(max = 200, message = "length of full name should not exceeding 200")
    private String fullName;
}

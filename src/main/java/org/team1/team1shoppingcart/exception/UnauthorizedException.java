package org.team1.team1shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 17:06
 * @Param
 **/

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super("Unauthorized: " + message);
    }
}

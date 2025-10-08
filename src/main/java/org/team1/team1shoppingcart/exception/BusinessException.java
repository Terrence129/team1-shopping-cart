package org.team1.team1shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 18:17
 * @Param
 **/

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super("BusinessException:" +  message);
    }
}

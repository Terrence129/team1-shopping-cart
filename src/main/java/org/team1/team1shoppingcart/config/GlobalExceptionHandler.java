package org.team1.team1shoppingcart.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.team1.team1shoppingcart.exception.BusinessException;

import java.util.Map;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:13
 * @Param
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> badReq(MethodArgumentNotValidException e){
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("success", false, "message", msg));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ie(Exception e){
        return ResponseEntity.status(500).body(Map.of("success", false, "message", e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessError(Exception e){
        return ResponseEntity.status(401).body(Map.of("success", false, "message", e.getMessage()));
    }
}

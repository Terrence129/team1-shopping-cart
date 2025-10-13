package org.team1.team1shoppingcart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.team1.team1shoppingcart.model.req.NewProductReq;
import org.team1.team1shoppingcart.service.AdminService;
import org.team1.team1shoppingcart.service.ProductService;

import java.util.Map;

/**
* @author chenyaqi
* @Description 
* @Date 12/10/25 22:39
* @Param
**/


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ProductService productService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(Map.of("success", adminService.login(username, password)));
    }

    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(@RequestPart("req") NewProductReq req, @RequestPart(value = "image", required = true) MultipartFile image){
        return ResponseEntity.ok(Map.of("success", adminService.createProductWithImages(image, req)));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(Map.of("success", adminService.deleteProduct(id)));
    }

    @PutMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(@RequestPart("req") NewProductReq req, @RequestPart(value = "image", required = false) MultipartFile image){

        return ResponseEntity.ok(Map.of("success", adminService.updateProduct(req, image)));
    }

}

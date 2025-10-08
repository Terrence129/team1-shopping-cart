package org.team1.team1shoppingcart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team1.team1shoppingcart.model.entity.Product;
import org.team1.team1shoppingcart.model.repository.ProductRepository;
import org.team1.team1shoppingcart.service.ProductService;

import java.util.List;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:14
 * @Param
 **/

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(defaultValue="0") int page,
                                     @RequestParam(defaultValue="12") int size,
                                     @RequestParam(required=false) String keyword) {
        return ResponseEntity.ok(productService.getProducts(page, size, keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/popular/list")
    public ResponseEntity<Page<Product>> getPopularProducts(@RequestParam(defaultValue="0") int page,
                                                            @RequestParam(defaultValue="12") int size) {
        return ResponseEntity.ok(productService.getPopularProducts(page, size));
    }
}

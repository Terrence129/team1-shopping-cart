package org.team1.team1shoppingcart.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.team1.team1shoppingcart.model.entity.Product;
import org.team1.team1shoppingcart.model.repository.ProductRepository;
import org.team1.team1shoppingcart.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Value("${file.upload-dir}")
    private String uploadDir;

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

    @GetMapping("/images/{imageName:.+}")
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        Path imagePath = Paths.get(uploadDir).resolve(imageName).normalize();
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            response.setContentType(Files.probeContentType(imagePath));
            response.setContentLength((int) resource.contentLength());

            try (InputStream inputStream = resource.getInputStream();
                 OutputStream outputStream = response.getOutputStream()) {
                IOUtils.copy(inputStream, outputStream);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

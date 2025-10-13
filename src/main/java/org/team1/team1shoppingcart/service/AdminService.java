package org.team1.team1shoppingcart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.team1.team1shoppingcart.exception.BusinessException;
import org.team1.team1shoppingcart.model.entity.CartItem;
import org.team1.team1shoppingcart.model.entity.Product;
import org.team1.team1shoppingcart.model.repository.CartItemRepository;
import org.team1.team1shoppingcart.model.repository.OrderItemRepository;
import org.team1.team1shoppingcart.model.repository.ProductRepository;
import org.team1.team1shoppingcart.model.req.NewProductReq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author chenyaqi
 * @Description
 * @Date 12/10/25 22:40
 * @Param
 **/


@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            log.info("Admin logged in");
            return true;
        } else {
            log.info("Username or password incorrect");
            return false;
        }
    }

    public boolean createProductWithImages(MultipartFile image, NewProductReq req) {
        // Validate image
        if (image.isEmpty()) {
            throw new BusinessException("You must provide an image file");
        }

        // Check file type
        String contentType = image.getContentType();
        if (!isImage(contentType)) {
            log.warn("None image format: {}", image.getOriginalFilename());
            throw new BusinessException("None image format");
        }

        String fileName = storeImage(image);
        req.setImageUrl(fileName);
        createProduct(req);
        return true;
    }

    private String storeImage(MultipartFile image) {
        try {
            // Store image and get path
            // Create upload directory if it doesn't exist
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            // Generate unique filename
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            File dest = new File(uploadPath, fileName);
            image.transferTo(dest);
            // Save file
            return fileName;
        } catch (Exception e) {
            log.error("Error creating product image", e);
            return null;
        }
    }

    private boolean isImage(String contentType) {
        return contentType != null && contentType.startsWith("image/");
    }

    public boolean createProduct(NewProductReq req){
        if (productRepository.findProductByName(req.getName()) != null) {
            throw new BusinessException("Product name already exists");
        }
        try {
            Product product = new Product();
            product.setName(req.getName());
            product.setDescription(req.getDescription());
            product.setPrice(req.getPrice());
            product.setStock(req.getStock());
            product.setImageUrl(req.getImageUrl());
            product.setVisible(true);
            product.setActive(true);
            product.setCreatedAt(Instant.now());
            product.setUpdatedAt(Instant.now());
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            log.error("Error while creating product", e);
            return false;
        }

    }

    @Transactional
    public boolean deleteProduct(Long id){
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new BusinessException("Product not found");
        }
        try {
            deleteImage(product.getImageUrl());
            cartItemRepository.deleteAllByProduct(product);
            orderItemRepository.deleteAllByProduct(product);
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            log.error("Error while deleting product", e);
            return false;
        }
    }

    private boolean isLocalUploadPath(String path) {
        if (!org.springframework.util.StringUtils.hasText(path)) return false;
        String s = path.trim();
        if (s.startsWith("http://") || s.startsWith("https://") || s.startsWith("/images/")) return false;
        return true;
    }

    private void deleteImage(String fileName) {
        try {
            if (!org.springframework.util.StringUtils.hasText(fileName)) return;
            if (!isLocalUploadPath(fileName)) return;

            Path path = Paths.get(uploadDir).resolve(fileName).normalize();
            Files.deleteIfExists(path);
        } catch (Exception ex) {
            log.warn("Delete image failed, ignore. fileName={}, msg={}", fileName, ex.getMessage());
        }
    }

    public boolean updateProduct(NewProductReq req, MultipartFile image) {
        Product product = productRepository.findProductById(req.getProductId());
        if (product == null) {
            throw new BusinessException("Product not found");
        }
        try {
            if (image != null) {
                // Check file type
                String contentType = image.getContentType();
                if (!isImage(contentType)) {
                    log.warn("None image format: {}", image.getOriginalFilename());
                    throw new BusinessException("None image format");
                }
                String newFileName = storeImage(image);
                deleteImage(product.getImageUrl());
                product.setImageUrl(newFileName);
            }
            if (req.getName() != null) {
                product.setName(req.getName());
            }
            if (req.getDescription() != null) {
                product.setDescription(req.getDescription());
            }
            if (req.getPrice() != null) {
                product.setPrice(req.getPrice());
            }
            if (req.getStock() != null) {
                product.setStock(req.getStock());
            }
            if (req.getVisible() != null) {
                product.setVisible(req.getVisible());
            }
            if (req.getActive() != null) {
                product.setActive(req.getActive());
            }
            product.setUpdatedAt(Instant.now());
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            log.error("Error while updating product", e);
            return false;
        }
    }
}

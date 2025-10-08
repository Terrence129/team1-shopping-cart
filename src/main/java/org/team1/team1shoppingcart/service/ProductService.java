package org.team1.team1shoppingcart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.team1.team1shoppingcart.model.entity.Product;
import org.team1.team1shoppingcart.model.repository.ProductRepository;

/**
 * @author chenyaqi
 * @Description
 * @Date 6/10/25 00:58
 * @Param
 **/

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(Integer page, Integer size, String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return (keyword==null || keyword.isBlank())
                ? productRepository.findByVisibleTrue(pageable)
                : productRepository.findByVisibleTrueAndNameContainingIgnoreCase(keyword, pageable);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> getPopularProducts(Integer page, Integer size) {
        return productRepository.findPopularProducts(PageRequest.of(page, size));
    }
}

package org.team1.team1shoppingcart.model.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.team1.team1shoppingcart.model.entity.Product;


/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:10
 * @Param
 **/
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    Page<Product> findByVisibleTrue(Pageable pageable);

    Page<Product> findByVisibleTrueAndNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.rating DESC, p.reviewCount DESC")
    Page<Product> findPopularProducts(Pageable pageable);

    // decrease stock
    @Transactional
    @Modifying
    @Query("update Product p set p.stock = p.stock - :amount where p.id = :productId")
    void decreaseStock(@Param("productId") Long productId, @Param("amount") Integer amount);

    // get stock
    @Query("select p.stock from Product p where p.id = :productId")
    Integer getStock(@Param("productId") Long productId);
}

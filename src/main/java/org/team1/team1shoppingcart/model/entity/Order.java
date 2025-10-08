package org.team1.team1shoppingcart.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "shopping_cart")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "order_number", nullable = false, length = 50)
    private String orderNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private org.team1.team1shoppingcart.model.entity.User user;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Size(max = 50)
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Size(max = 100)
    @Column(name = "recipient_name", length = 100)
    private String recipientName;

    @Size(max = 20)
    @Column(name = "recipient_phone", length = 20)
    private String recipientPhone;

    @Size(max = 500)
    @Column(name = "shipping_addr", length = 500)
    private String shippingAddr;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "cancelled_at")
    private Instant cancelledAt;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "paid_at")
    private Instant paidAt;

}
package com.phung.clothshop.domain.entity.order;

import java.util.Date;

import javax.persistence.*;

import com.phung.clothshop.domain.BaseEntity;
import com.phung.clothshop.domain.dto.order.OrderItemDTO;
import com.phung.clothshop.domain.dto.order.OrderItemResDTO;
import com.phung.clothshop.domain.entity.product.Product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column
    private String variation;

    @Column
    private Long quantity;

    @Column
    private Long total;

    public OrderItemDTO toOrderItemDTO() {
        return new OrderItemDTO()
                .setOrderItemID(id)
                .setOrderID(order.getId())
                .setProductID(product.getId())
                .setName(product.getName())
                .setPrice(product.getPriceTotal())
                .setVariation(variation)
                .setQuantity(quantity)
                .setTotal(quantity * product.getPriceTotal())
                ;
    }

    public OrderItemResDTO tOrderItemResDTO() {
        return new OrderItemResDTO()
                .setId(id)
                .setOrderID(order.getId())
                .setProduct(product.toProductResDTO())
                .setVariation(variation)
                .setQuantity(quantity)
                .setTotal(quantity * product.getPriceTotal())
                ;
    }

}

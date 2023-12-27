package com.phung.clothshop.model.order;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import com.phung.clothshop.model.BaseEntity;
import com.phung.clothshop.model.dto.order.CartItemDTO;
import com.phung.clothshop.model.product.Product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
@Where(clause = "deleted = false")
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column
    private String variation;

    @Column
    private Long quantity;

    @Column
    private Long total;

    public CartItemDTO toCartItemDTO() {
        return new CartItemDTO()
                .setCartItemId(id)
                .setCartId(cart.getId())
                .setProductId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setVariation(variation)
                .setQuantity(quantity)
                .setTotal(total);
    }

    public OrderItem toOrderItem(Order order, Product product) {
        return new OrderItem()
                .setOrder(order)
                .setProduct(product)
                .setVariation(variation)
                .setQuantity(quantity)
                .setTotal(product.getPrice() * quantity);

    }

}

package com.phung.clothshop.model.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.phung.clothshop.model.BaseEntity;
import com.phung.clothshop.model.dto.order.CartDTO;
import com.phung.clothshop.model.dto.order.CartItemDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", targetEntity = CartItem.class, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    public CartDTO toCartDTO() {

        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartItemDTOs.add(cartItem.toCartItemDTO());
        }

        return new CartDTO()
                .setCartId(id)
                .setCartItemDTOs(cartItemDTOs);
    }

}

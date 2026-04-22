package com.ecommerce.graphql;

import com.ecommerce.model.CartItem;
import com.ecommerce.service.CartService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CartResolver {

    private final CartService cartService;

    public CartResolver(CartService cartService) {
        this.cartService = cartService;
    }

    @QueryMapping
    public List<CartItem> myCart(@Argument int userId) {
        return cartService.getCartItems(userId);
    }
}

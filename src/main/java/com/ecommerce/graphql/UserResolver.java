package com.ecommerce.graphql;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * GraphQL resolver for User queries.
 */
@Controller
public class UserResolver {

    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> users() {
        // Return all users (no pagination for simplicity in this specific query)
        return userService.getAllUsers(0, Integer.MAX_VALUE, "name", "asc", null).getContent();
    }

    @SchemaMapping
    public List<Order> orders(User user) {
        return user.getOrders();
    }

    @SchemaMapping(field = "cart")
    public List<CartItem> cart(User user) {
        return user.getCartItems();
    }
}

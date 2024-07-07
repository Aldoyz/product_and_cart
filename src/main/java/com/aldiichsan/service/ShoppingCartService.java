package com.aldiichsan.service;

import com.aldiichsan.model.*;

import java.util.List;

public interface ShoppingCartService {
    CheckOutCartModel getCartDetails(Long customerId);
    void addItemToCart(CartDetailsInsertModel body);
    void checkOutItem(CheckOutCartModel body);
    void deleteOneItem(Long id);
}

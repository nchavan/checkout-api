package com.checkout.promotions.interfaces;

import com.checkout.model.Product;

import java.util.Map;

public interface ProductDiscount {
    double calculateDiscount(final Map<Product, Integer> shoppingCart);
}

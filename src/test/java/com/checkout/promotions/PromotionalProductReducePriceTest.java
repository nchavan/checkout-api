package com.checkout.promotions;

import com.checkout.model.Product;
import com.checkout.services.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromotionalProductReducePriceTest {
    private Product item1;
    private Product item2;
    private Product item3;
    private Cart cart;

    @BeforeEach
    public void initialize() {
        item1 = new Product("001", "Travel Card Holder", 9.25);
        item2 = new Product("002", "Personalised cufflinks", 45.00);
        item3 = new Product("003", "Kids T-shirt", 19.95);
        cart = new Cart();
        cart.addProduct(item1);
        cart.addProduct(item2);
    }

    @DisplayName("Add promotional discount to Cart and return discounted value")
    @Test
    public void addProductsToCartAndReturnPromotionalDiscountValue() {
        double expectedDiscountValue = 2.50;
        cart.addProduct(item1);

        PromotionalProductReducePrice promotions = new PromotionalProductReducePrice(item1, 2, 8.00);

        double output = promotions.calculateDiscount(cart.getItems());
        assertEquals(expectedDiscountValue, output);
    }

    @DisplayName("No promotional discount applied to Cart so return value as zero")
    @Test
    public void addProductsToCartAndReturnZeroDiscountValue() {
        double expectedDiscountValue = 0.00;
        cart.addProduct(item3);

        PromotionalProductReducePrice promotions = new PromotionalProductReducePrice(item1, 2, 8.00);

        double output = promotions.calculateDiscount(cart.getItems());
        assertEquals(expectedDiscountValue, output);
    }
}
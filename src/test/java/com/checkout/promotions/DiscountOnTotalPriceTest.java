package com.checkout.promotions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountOnTotalPriceTest {

    @DisplayName("Add 10% discount on total price and return discounted value")
    @Test
    public void applyPercentDiscountAndReturnDiscountValue() {
        double expectedDiscountValue = 15.00;
        DiscountOnTotalPrice applyDiscountTotalCart = new DiscountOnTotalPrice(100, 10);

        double output = applyDiscountTotalCart.calculateDiscount(150);
        assertEquals(expectedDiscountValue, output);
    }

    @DisplayName("No promotional discount applied so return discounted value as zero")
    @Test
    public void noDiscountAppliedToCartAndReturnDiscountValueAsZero() {
        double expectedDiscountValue = 0.0;
        DiscountOnTotalPrice applyDiscountTotalCart = new DiscountOnTotalPrice(500, 10);

        double output = applyDiscountTotalCart.calculateDiscount(100);
        assertEquals(expectedDiscountValue, output);
    }
}
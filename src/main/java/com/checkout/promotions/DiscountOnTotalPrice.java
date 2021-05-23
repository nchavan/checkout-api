package com.checkout.promotions;

import com.checkout.promotions.interfaces.CartDiscount;

public class DiscountOnTotalPrice implements CartDiscount {

    private final double totalAmount;
    private final int discountPercent;

    public DiscountOnTotalPrice(final double totalAmount, final int discountPercent) {
        this.totalAmount = totalAmount;
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateDiscount(final double totalPrice) {
        if (totalPrice >= totalAmount) {
            return totalPrice/100 * this.discountPercent;
        }
        return 0.0;
    }
}

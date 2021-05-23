package com.checkout.promotions;

import com.checkout.model.Product;
import com.checkout.promotions.interfaces.CartDiscount;
import com.checkout.promotions.interfaces.ProductDiscount;

import java.util.HashSet;
import java.util.Set;

public class PromotionalRules {
    private final Set<ProductDiscount> productDiscounts = new HashSet<>();
    private final Set<CartDiscount> cartDiscounts = new HashSet<>();

    public void buyMultipleSameProductsAndReduceProductPrice(final Product product, final Integer itemsCount, double newPrice) {
        ProductDiscount productDiscount = new PromotionalProductReducePrice(product, itemsCount, newPrice);
        this.productDiscounts.add(productDiscount);
    }

    public void applyTotalDiscountOnCart(final double totalAmount, final int discountPercent) {
        CartDiscount productDiscount = new DiscountOnTotalPrice(totalAmount, discountPercent);
        this.cartDiscounts.add(productDiscount);
    }

    public Set<ProductDiscount> getProductDiscounts() {
        return Set.copyOf(productDiscounts);
    }

    public Set<CartDiscount> getCartDiscounts() {
        return cartDiscounts;
    }

    public void clearProductPromotions() {
        productDiscounts.clear();
    }

    public void clearCartPromotions() {
        cartDiscounts.clear();
    }
}

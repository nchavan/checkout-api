package com.checkout.promotions;

import com.checkout.model.Product;
import com.checkout.promotions.interfaces.ProductDiscount;

import java.util.Map;

public class PromotionalProductReducePrice implements ProductDiscount {
    private final Product product;
    private final int itemsCount;
    private final double newPrice;

    public PromotionalProductReducePrice(final Product product, final int itemsCount, final double newPrice) {
        this.product = product;
        this.itemsCount = itemsCount;
        this.newPrice = newPrice;
    }

    @Override
    public double calculateDiscount(final Map<Product, Integer> shoppingCart) {
        double discount = 0.0;
        if (shoppingCart.containsKey(product)) {
            if(shoppingCart.get(product) >= itemsCount) {
                discount = discount + (product.getPrice() - newPrice) * shoppingCart.get(product);
            }
        }
        return discount;
    }
}

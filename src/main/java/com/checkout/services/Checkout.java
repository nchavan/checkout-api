package com.checkout.services;

import com.checkout.model.Product;
import com.checkout.exception.InvalidInput;
import com.checkout.promotions.PromotionalRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Checkout {
    private final Logger logger = LoggerFactory.getLogger(Checkout.class);
    private final String serviceName = "CheckoutService:: ";

    private final Cart cart;
    private final PromotionalRules promotionalRules;

    public Checkout(final PromotionalRules promotionalRules) {
        cart = new Cart();
        this.promotionalRules = promotionalRules;
    }

    public void scan(final Product product) throws InvalidInput {
        if (product == null) {
            logger.error("{} invalid product with null value, please scan valid product", serviceName);
            throw new InvalidInput("Invalid product with null value entered, please enter a valid product");
        }
        logger.info("{} cart id {}, scan product: {}({})", serviceName, cart.getId(), product.getName(), product.getCode());
        cart.addProduct(product);
    }

    public String total() {
        if (this.promotionalRules != null && !this.promotionalRules.getProductDiscounts().isEmpty()) {
            this.promotionalRules.getProductDiscounts()
                    .stream()
                    .mapToDouble(productDiscount -> productDiscount.calculateDiscount(cart.getItems()))
                    .forEach(cart::applyDiscount);

            this.promotionalRules.getCartDiscounts()
                    .stream()
                    .mapToDouble(cartDiscount -> cartDiscount.calculateDiscount(cart.getTotalPrice()))
                    .forEach(cart::applyDiscount);
        }
        logger.info("{} cart id {}, total price is {}", serviceName, cart.getId(), cart.getTotalPriceInCurrency());
        return cart.getTotalPriceInCurrency();
    }
}

package com.checkout;

import com.checkout.model.Product;
import com.checkout.services.Checkout;
import com.checkout.exception.InvalidInput;
import com.checkout.promotions.PromotionalRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvalidInput {
        String serviceName = "Checkout API:: ";

        Product travelCardHolder = new Product("001", "Travel Card Holder", 9.25);
        Product personalisedCufflinks = new Product("002", "Personalised cufflinks", 45.00);
        Product kidsTShirt = new Product("003", "Kids T-shirt", 19.95);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(travelCardHolder,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        logger.info("{} starting", serviceName);
        Checkout co = new Checkout(promotionalRules);
        co.scan(travelCardHolder);
        co.scan(personalisedCufflinks);
        co.scan(travelCardHolder);
        co.scan(kidsTShirt);
        co.total();
        logger.info("{} stopping", serviceName);
    }
}

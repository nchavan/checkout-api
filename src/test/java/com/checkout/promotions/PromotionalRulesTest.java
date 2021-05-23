package com.checkout.promotions;

import com.checkout.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromotionalRulesTest {

    @DisplayName("Add 0 promotional rule and and return list with 1 rule")
    @Test
    public void apply0PromotionalRuleAndReturnListPromotionalRule() {
        PromotionalRules promotionalRules = new PromotionalRules();

        assertEquals(promotionalRules.getProductDiscounts().size(), 0);
    }

    @DisplayName("Add 1 promotional rule and and return list with 1 rule")
    @Test
    public void apply1PromotionalRuleAndReturnListPromotionalRule() {
        Product item1 = new Product("001", "Travel Card Holder", 50);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1, 2, 20);

        assertEquals(promotionalRules.getProductDiscounts().size(), 1);
    }

    @DisplayName("Add 2 promotional rule and and return list with 1 rule")
    @Test
    public void apply2PromotionalRuleAndReturnListPromotionalRule() {
        Product item1 = new Product("001", "Travel Card Holder", 50);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1, 2, 20);
        promotionalRules.applyTotalDiscountOnCart(1000, 10);

        assertEquals(promotionalRules.getProductDiscounts().size(), 1);
        assertEquals(promotionalRules.getCartDiscounts().size(), 1);
    }

    @DisplayName("Clear product and card promotional rules then return promotions with size 0")
    @Test
    public void apply2PromotionalRuleAndClearPromotionsAndReturnListPromotionalRule() {
        Product item1 = new Product("001", "Travel Card Holder", 50);
        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1, 2, 20);
        promotionalRules.applyTotalDiscountOnCart(1000, 10);

        promotionalRules.clearProductPromotions();
        promotionalRules.clearCartPromotions();

        assertEquals(promotionalRules.getProductDiscounts().size(), 0);
        assertEquals(promotionalRules.getCartDiscounts().size(), 0);
    }
}
package com.checkout.services;

import com.checkout.exception.InvalidInput;
import com.checkout.model.Product;
import com.checkout.promotions.PromotionalRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @DisplayName("Cart with no product and promotions then return zero total")
    @Test
    public void whenCartWithNoItemsThenReturnTotal() {
        String expectedPrice = "£0.00";
        Product item1 = new Product("001", "Travel Card Holder", 9.25);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        Checkout co = new Checkout(promotionalRules);
        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("Cart with one product and null promotions then return product price")
    @Test
    public void whenCartWithNullPromotionsThenReturnTotal() throws InvalidInput {
        String expectedPrice = "£29.20";
        Product item1 = new Product("001", "Travel Card Holder", 9.25);
        Product item3 = new Product("003", "Kids T-shirt", 19.95);

        Checkout co = new Checkout(null);
        co.scan(item1);
        co.scan(item3);

        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("Cart with null product and null promotions then receive invalid input exception")
    @Test
    public void whenCartWithNullPromotionsAndNullProductsThenReturnInputException() {
        Checkout co = new Checkout(null);
        Assertions.assertThrows(InvalidInput.class, () -> co.scan(null));
    }

    @DisplayName("duplicate promotional rules ignore duplicate promotions apply discount and return total")
    @Test
    public void whenDuplicatePromotionalRulesAddedThenReturnTotalPrice() throws InvalidInput {
        String expectedPrice = "£54.25";
        Product item1 = new Product("001", "Travel Card Holder", 9.25);
        Product item2 = new Product("002", "Personalised cufflinks", 45.00);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        Checkout co = new Checkout(promotionalRules);
        co.scan(item1);
        co.scan(item2);

        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("Cart with total price greater then expected apply discount and return total price")
    @Test
    public void whenCartWithDifferentProductsThenApplyDiscountAndReturnPrice() throws InvalidInput {
        String expectedPrice = "£54.25";
        Product item1 = new Product("001", "Travel Card Holder", 9.25);
        Product item2 = new Product("002", "Personalised cufflinks", 45.00);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        Checkout co = new Checkout(promotionalRules);
        co.scan(item1);
        co.scan(item2);

        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("Cart with total price greater then discounted total price then apply discount and return total")
    @Test
    public void whenCartPriceAboveDiscountTotalThenApplyDiscountReturnPrice() throws InvalidInput {
        String expectedPrice = "£66.78";
        Product item1 = new Product("001", "Travel Card Holder", 9.25);
        Product item2 = new Product("002", "Personalised cufflinks", 45.00);
        Product item3 = new Product("003", "Kids T-shirt", 19.95);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        Checkout co = new Checkout(promotionalRules);
        co.scan(item1);
        co.scan(item2);
        co.scan(item3);

        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("Cart with promotional products so reduce price and apply discount and return total")
    @Test
    public void whenCartWithSamePromotionalProductThenApplyDiscountAndReturnTotal() throws InvalidInput {
        String expectedPrice = "£36.95";
        Product item1 = new Product("001", "Travel Card Holder", 9.25);
        Product item3 = new Product("003", "Kids T-shirt", 19.95);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(item1,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        Checkout co = new Checkout(promotionalRules);
        co.scan(item1);
        co.scan(item3);
        co.scan(item1);
        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("Cart with promotional products so reduce price and then apply percentage discount and return total")
    @Test
    public void whenCartWithPromotionalProductsAndTotalDiscountPercentAppliedThenReturnTotal() throws InvalidInput {
        String expectedPrice = "£73.76";
        Product travelCardHolder = new Product("001", "Travel Card Holder", 9.25);
        Product personalisedCufflinks = new Product("002", "Personalised cufflinks", 45.00);
        Product kidsTShirt = new Product("003", "Kids T-shirt", 19.95);

        PromotionalRules promotionalRules = new PromotionalRules();
        promotionalRules.buyMultipleSameProductsAndReduceProductPrice(travelCardHolder,2, 8.50);
        promotionalRules.applyTotalDiscountOnCart(60, 10);

        Checkout co = new Checkout(promotionalRules);
        co.scan(travelCardHolder);
        co.scan(personalisedCufflinks);
        co.scan(travelCardHolder);
        co.scan(kidsTShirt);

        String price = co.total();
        assertEquals(price, expectedPrice);
    }

    @DisplayName("No Promotions so return total price")
    @Test
    public void whenNoPromotionsThenReturnTotal() throws InvalidInput {
        String expectedPrice = "£83.45";
        Product travelCardHolder = new Product("001", "Travel Card Holder", 9.25);
        Product personalisedCufflinks = new Product("002", "Personalised cufflinks", 45.00);
        Product kidsTShirt = new Product("003", "Kids T-shirt", 19.95);

        PromotionalRules promotionalRules = new PromotionalRules();

        Checkout co = new Checkout(promotionalRules);
        co.scan(travelCardHolder);
        co.scan(personalisedCufflinks);
        co.scan(travelCardHolder);
        co.scan(kidsTShirt);

        String price = co.total();
        assertEquals(price, expectedPrice);
    }
}
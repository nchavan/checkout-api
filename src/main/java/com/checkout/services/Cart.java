package com.checkout.services;

import com.checkout.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();
    private final String id;
    private double totalPrice = 0.0;
    private double totalDiscount = 0.0;

    public Cart() {
        this.id = UUID.randomUUID().toString();
    }

    public Map<Product, Integer> getItems() {
        return new HashMap<>(items);
    }

    public String getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice - totalDiscount;
    }

    public String getTotalPriceInCurrency() {
        double total = doublePrecision(totalPrice - totalDiscount);
        return priceCurrency(total, Locale.UK);
    }

    public void applyDiscount(final double discount) {
        totalDiscount +=discount;
    }

    public void addProduct(final Product product){
        if (product != null) {
            if(items.containsKey(product)){
                Integer productCount = items.get(product);
                items.put(product, productCount + 1);
            } else {
                items.put(product, 1);
            }
            totalPrice += product.getPrice();
        }
    }

    private double doublePrecision(final double discount) {
        return BigDecimal.valueOf(discount).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    private String priceCurrency(final double total, Locale locale) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(total);
    }
}

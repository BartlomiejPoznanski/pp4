package pl.bpoznanski.ecommerce.sales.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    Map<String, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public void addProduct(String productId) {
        if (!isInCart(productId)) {
            putIntoCart(productId);
        } else {
            increaseProductQty(productId);
        }
    }
    private boolean isInCart(String productId) {
        return products.containsKey(productId);
    }
    private void putIntoCart(String productId) {
        products.put(productId, 1);
    }
    private void increaseProductQty(String productId) {
        products.put(productId, products.get(productId) + 1);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public Integer getItemsCount() {
        return products.size();
    }

    public List<CartItem> getItems() {
        return products.entrySet().stream()
                .map(es -> new CartItem(es.getKey(), es.getValue()))
                .toList();
    }
}
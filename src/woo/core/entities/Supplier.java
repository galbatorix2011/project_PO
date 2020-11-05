package woo.core.entities;

import java.util.Map;
import java.util.HashMap;
import woo.core.products.Product;

public class Supplier extends Entity {

    private boolean _active;
    private Map<String, Product> _products;

    public Supplier(String key, String name, String address) {
        super(key, name, address);
        _active = true;
        _products = new HashMap<>();
    }

    public boolean switchOnOff() {
        // tentar alterar
        _active = _active ? false : true;
        return _active;
    }

    public void addProduct(Product product) {
        _products.put(product.getKey(), product);
    }

    public boolean containsProduct(String key) {
        return _products.containsKey(key);
    }
}
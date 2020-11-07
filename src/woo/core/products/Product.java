package woo.core.products;

import woo.core.entities.Supplier;

public abstract class Product {
    private String _key;
    private int _price;
    private int _criticalValue;
    private int _value;
    private Supplier _supplier;

    public Product(String key, int price, int criticalValue, Supplier supplier, int value){
        _key = key;
        _price = price;
        _criticalValue = criticalValue;
        _supplier = supplier;
        _value = value;
    }

    public Supplier getSupplier(){
        return _supplier;
    }

    public String getKey(){
        return _key;
    }

    public int getPrice(){
        return _price;
    }

    public int getCriticalValue(){
        return _criticalValue;
    }

    public int getValue(){
        return _value;
    }

    public void setPrice(int newPrice){
        _price = newPrice;
    }

    public void addValue(int amount){
        _value += amount;
    }

    public boolean removeValue(int amount){
        if (_value - amount > 0){
            _value -= amount;
            return true;
        }
        else
            return false;
    }

    public String toString(){
        return _key + "|" + _supplier.getKey() + "|" + _price + "|" + _criticalValue+ "|" + _value;
    }
}

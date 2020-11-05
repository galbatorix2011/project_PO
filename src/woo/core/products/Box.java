package woo.core.products;

import woo.core.entities.Supplier;

public class Box extends Product{

    private TypeService _typeService;

    public Box(String key, int price, int criticalValue, Supplier supplier, TypeService typeService){
        super(key, price, criticalValue, supplier);
        _typeService = typeService;
    }

    public TypeService getTypeService(){
        return _typeService;
    }

    public String toString(){
        return "BOX|" + super.toString() + "|" + _typeService;
    }
}


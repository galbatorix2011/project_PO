package woo.core.products;

import woo.core.entities.Supplier;
public class Container extends Box{

    private QualityService _qualityService;

    public Container(String key, int price, int criticalValue, Supplier supplier, TypeService typeService, QualityService qualityService){
        super(key, price, criticalValue, supplier, typeService);
        _qualityService = qualityService;
    }

    public QualityService getQualityService(){
        return _qualityService;
    }

    public String toString(){
        return "CONTAINER|" + getKey() + "|" + getSupplier().getKey() + "|" + getPrice() + "|" +
                getCriticalValue() + "|" + getValue() + '|' + getQualityService();
    }
}
package woo.core.entities;

public class Client extends Entity{
    private int _points;
    private double _payedSales;
    private double _totalValues;

    public Client(String key, String name, String address){
        super(key, name, address);
        _points = 0;
    }
    public String toString(){
        return super.toString() + "|" + _totalValues + "|" + _payedSales;
    }

    public boolean equals(Object o){
        return o instanceof Client && getKey().equals(((Client) o).getKey());
    }
}

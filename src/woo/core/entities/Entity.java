package woo.core.entities;

public abstract class Entity{

    private String _key;
    private String _name;
    private String _adress;

    public Entity(String key, String name, String adress){
        _key = key;
        _name = name;
        _adress = adress;
    }

    public String getKey(){
        return _key;
    }

    public String getName(){
        return _name;
    }

    public String getAdress(){
        return _adress;
    }

    public String toString(){
        return _key + "|" + _name + "|" + _adress;
    }
}

package woo.core.products;

import woo.core.entities.Supplier;

public class Book extends Product{

    private String _title;
    private String _author;
    private String _isbn;

    public Book(String key, int price, int criticalValue, Supplier supplier, String title, String author, String isbn, int value){
        super(key, price, criticalValue, supplier, value);
        _title = title;
        _isbn = isbn;
        _author = author;
    }

    public String getTitle(){
        return _title;
    }

    public String getAuthor(){
        return _author;
    }

    public String getIsbn(){
        return _isbn;
    }

    public String toString(){
        return "BOOK|" + super.toString() + "|" + _title + "|" + _author + "|" + _isbn;
    }
}
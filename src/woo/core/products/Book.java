package woo.core.products;

import woo.core.entities.Supplier;

public class Book extends Product{

    private String _title;
    private String _author;
    private String _isbn;

    public Book(String key, String title, String isbn, String author, int price, int criticalValue, Supplier supplier){
        super(key, price, criticalValue, supplier);
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
        return "Book|" + super.toString() + "|" + _title + "|" + _author + "|" + _isbn;
    }
}
package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;
import woo.core.products.*;
import woo.core.entities.Supplier;
import woo.core.entities.Client;
import woo.core.exception.*;

import java.util.Map;
import java.util.HashMap;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  // FIXME define attributes
  private int _date;
  private Map<String, Client> _clients;
  private Map<String, Supplier> _suppliers;
  private Map<String, Product> _products;
  // FIXME define contructor(s)

  public Store() {
    _date = 0;
    _clients = new HashMap<String, Client>();
    _suppliers = new HashMap<String, Supplier>();
    _products = new HashMap<String, Product>();
  }

  // FIXME define methods
  public String getSupplier(String key) throws WrongSupplierKeyException{
    if (!(_suppliers.containsKey(key)))
      throw new WrongSupplierKeyException(key);
    return _suppliers.get(key).toString();
  }

  public String getClient(String key) throws WrongClientKeyException{
    if(!(_clients.containsKey(key)))
      throw new WrongClientKeyException(key);
    return _clients.get(key).toString();
  }

  public String getAllClients(){
    return getAllElements(_clients);
  }

  public void registerClient(String key, String name, String address) throws DuplicateClientException{
    Client newClient;
    if (_clients.containsKey(key))
      throw new DuplicateClientException(key);
    newClient = new Client(key, name, address);
    _clients.put(key, newClient);
  }

  public String getAllSuppliers(){
    return getAllElements(_suppliers);
  }

  private <E> String getAllElements(Map<String,E> map){
    String str = "";
    for (E entity: map.values())
      str += entity.toString() + "\n";
    return str.equals("") ? "" : str.substring(0, str.length() - 1);
  }

  public void registerSupplier(String key, String name, String address) throws DuplicateSupplierException{
    Supplier newSupplier;
    if (hasSupplier(key))
      throw new DuplicateSupplierException(key);
    newSupplier = new Supplier(key, name, address);
    _suppliers.put(key, newSupplier);
  }

  private boolean hasSupplier(String key){
    return _suppliers.containsKey(key);
  }

  private void canCreateProduct(String supplierKey, String productKey)
          throws NonExistentSupplierException, DuplicateProductException{
    if (!hasSupplier(supplierKey))
      throw new NonExistentSupplierException(supplierKey);
    if (_products.containsKey(productKey))
      throw new DuplicateProductException(productKey);
  }

  public void registerBox(String productKey, int price, int criticalValue, String supplierKey, String typeServiceString, int value)
          throws NonExistentSupplierException, DuplicateProductException, WrongServiceTypeException {
    canCreateProduct(supplierKey, productKey);
    TypeService typeService;

    try{
      typeService = TypeService.valueOf(typeServiceString);
    } catch (IllegalArgumentException e){
      throw new WrongServiceTypeException(typeServiceString);
    }

    Supplier supplier = _suppliers.get(supplierKey);
    Product newProduct = new Box(productKey, price, criticalValue, supplier, typeService, value);
    _products.put(productKey, newProduct);
    supplier.addProduct(newProduct);
  }

  public void registerContainer(String productKey, int price, int criticalValue, String supplierKey,
          String typeServiceString, String qualityServiceString, int value) throws NonExistentSupplierException,
          DuplicateProductException, WrongServiceTypeException, WrongServiceLevelException{
    canCreateProduct(supplierKey, productKey);
    TypeService typeService;
    QualityService qualityService;

    try{
      typeService = TypeService.valueOf(typeServiceString);
    } catch (IllegalArgumentException e2){
      throw new WrongServiceTypeException(typeServiceString);
    }
    try {
      qualityService = QualityService.valueOf(qualityServiceString);
    } catch (IllegalArgumentException e1){
      throw new WrongServiceLevelException(qualityServiceString);
    }

    Supplier supplier = _suppliers.get(supplierKey);
    Product newProduct = new Container(productKey, price, criticalValue, supplier, typeService, qualityService, value);
    _products.put(productKey, newProduct);
    supplier.addProduct(newProduct);
  }

  public void registerBook(String productKey, int price, int criticalValue, String supplierKey, String title,
          String author, String isbn, int value) throws NonExistentSupplierException, DuplicateProductException{
    canCreateProduct(supplierKey, productKey);
    Supplier supplier = _suppliers.get(supplierKey);
    Product newProduct = new Book(productKey, price, criticalValue, supplier, title, author, isbn, value);
    _products.put(productKey, newProduct);
    supplier.addProduct(newProduct);
  }

  public String getAllProducts(){
    return getAllElements(_products);
  }


  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException, DuplicateClientException, WrongServiceLevelException, NonExistentSupplierException, DuplicateProductException, DuplicateSupplierException, WrongServiceTypeException {
    MyParser parser = new MyParser(this);
    parser.parseFile(txtfile);
  }

}

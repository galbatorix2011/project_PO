package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;

import woo.core.entities.Client;
import woo.core.exception.UnavailableFileException;
import woo.core.exception.MissingFileAssociationException;
import woo.core.exception.ImportFileException;
import woo.core.exception.BadEntryException;
import woo.core.exception.*;
import java.util.Map;
import java.util.HashMap;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();

  //FIXME define other attributes
  private int _date;
  //FIXME define constructor(s)
  //FIXME define other methods

  public int getDate(){
    return _date;
  }

  public void advanceDate(int days) throws InvalidDaysException{
    if (days <= 0)
      throw new InvalidDaysException(days);
    _date += days;
  }

  public String getClient(String key) throws WrongClientKeyException {
    return _store.getClient(key);
  }

  public String getAllClients(){
    return _store.getAllClients();
  }

  public void registerClient(String key, String name, String address) throws DuplicateClientException {
    _store.registerClient(key, name, address);
  }

  public String getAllSuppliers(){
    return _store.getAllSuppliers();
  }

  public void registerSupplier(String key, String name, String address) throws DuplicateSupplierException {
    _store.registerSupplier(key, name, address);
  }

  public void registerBox(String productKey, int price, int criticalValue, String supplierKey, String typeServiceString)
          throws NonExistentSupplierException, DuplicateProductException, WrongServiceTypeException {
    _store.registerBox(productKey, price, criticalValue, supplierKey, typeServiceString, 0);
  }

  public void registerContainer(String productKey, int price, int criticalValue, String supplierKey,
                                String typeServiceString, String qualityServiceString) throws NonExistentSupplierException,
          DuplicateProductException, WrongServiceTypeException, WrongServiceLevelException{
    _store.registerContainer(productKey, price, criticalValue, supplierKey, typeServiceString,
            qualityServiceString, 0);
  }

  public void registerBook(String productKey, int price, int criticalValue, String supplierKey, String title,
           String author, String isbn) throws NonExistentSupplierException, DuplicateProductException{
    _store.registerBook(productKey, price, criticalValue, supplierKey, title, author, isbn, 0);
  }

  public String getAllProducts(){
    return _store.getAllProducts();
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException, DuplicateClientException, WrongServiceLevelException, NonExistentSupplierException, DuplicateProductException, DuplicateSupplierException, WrongServiceTypeException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

}

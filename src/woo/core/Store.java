package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;
import woo.core.products.*;
import woo.core.entities.Supplier;
import woo.core.entities.Client;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import woo.app.exception.*;

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
  public Supplier getSupplier(String key) throws UnknownSupplierKeyException{
    if (!(_suppliers.containsKey(key)))
      throw new UnknownSupplierKeyException(key);
    return _suppliers.get(key);
  }

  public Client getClient(String key) throws UnknownClientKeyException{
    if(!(_clients.containsKey(key)))
      throw new UnknownClientKeyException(key);
    return _clients.get(key);
  }

  public Map<String, Client> getAllClients(){
    return Collections.unmodifiableMap(_clients);
  }

  public void registerClient(String key, String name, String address) throws DuplicateClientKeyException{
    Client newClient;
    if(_clients.containsKey(key))
      throw new DuplicateClientKeyException(key);
    newClient = new Client(key, name, address);
    _clients.put(key, newClient);
  }


  public void changePrice(String key, int price) {
  }


  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

}

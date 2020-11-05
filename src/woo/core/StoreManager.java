package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;

import woo.app.exception.DuplicateClientKeyException;
import woo.app.exception.UnknownClientKeyException;
import woo.core.entities.Client;
import woo.core.exception.UnavailableFileException;
import woo.core.exception.MissingFileAssociationException;
import woo.core.exception.ImportFileException;
import woo.core.exception.BadEntryException;
import woo.app.exception.InvalidDateException;
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

  public void advanceDate(int days) throws InvalidDateException{
    if (days <= 0)
      throw new InvalidDateException(days);
    _date += days;
  }

  public String getClient(String key) throws UnknownClientKeyException {
    return _store.getClient(key).toString();
  }

  public String getAllClients(){
    Map<String, Client> clients = _store.getAllClients();
    String clientsToString = "";
    for (Client c: clients.values())
      clientsToString += c.toString() + "\n";
   return clientsToString.substring(0, clientsToString.length() - 1);
  }

  public void registerClient(String key, String name, String address) throws DuplicateClientKeyException {
    _store.registerClient(key, name, address);
  }

  public void registerBox(String Key){


  }

  public void registerContainer(String Key){


  }

  public void registerBook(String key){

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
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

}

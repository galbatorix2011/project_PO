package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateClientKeyException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateClientException;
//FIXME import other classes

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<StoreManager> {

  //FIXME add input fields
  private Input<String> _key;
  private Input<String> _name;
  private Input<String> _address;

  public DoRegisterClient(StoreManager storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
    _name = _form.addStringInput(Message.requestClientName());
    _address = _form.addStringInput(Message.requestClientAddress());
    //FIXME init input fields
  }

  @Override
  public void execute() throws DialogException, DuplicateClientKeyException {
    String key;
    String name;
    String address;
    _form.parse();
    key = _key.value();
    name = _name.value();
    address = _address.value();
    try {
      _receiver.registerClient(key, name, address);
    } catch(DuplicateClientException e){
      throw new DuplicateClientKeyException(e.getMessage());
    }
  }

}

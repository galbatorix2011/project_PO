package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateClientKeyException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateSupplierException;
//FIXME import other classes

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<StoreManager> {

  private Input<String> _key;
  private Input<String> _name;
  private Input<String> _address;

  public DoRegisterSupplier(StoreManager receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    _key = _form.addStringInput(Message.requestSupplierKey());
    _name = _form.addStringInput(Message.requestSupplierName());
    _address = _form.addStringInput(Message.requestSupplierAddress());
  }

  @Override
  public void execute() throws DialogException {
    String key;
    String name;
    String address;
    _form.parse();
    key = _key.value();
    name = _name.value();
    address = _address.value();
    try{
      _receiver.registerSupplier(key, name, address);
    } catch(DuplicateSupplierException e){
      throw new DuplicateClientKeyException(e.getMessage());
    }
  }
}

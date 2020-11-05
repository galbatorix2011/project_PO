package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  private Input<String> _key;

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _key = _form.addStringInput(Message.requestClientKey());
    //FIXME init input fields
  }

  @Override
  public void execute() throws DialogException {
    String key;
    _form.parse();
    key = _key.value();
    String clientToString;
    try {
      clientToString = _receiver.getClient(key);
      _display.addLine(clientToString);
    } catch (UnknownClientKeyException e){
      _display.addLine(e.getMessage());
    } finally {
      _display.display();
    }
  }
}

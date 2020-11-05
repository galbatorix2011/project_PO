package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Show current date.
 */
public class DoDisplayDate extends Command<StoreManager> {

  //FIXME add input fields

  public DoDisplayDate(StoreManager receiver) {
    super(Label.SHOW_DATE, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    _display.addLine(Message.currentDate(_receiver.getDate()));
    _display.display();
  }
}

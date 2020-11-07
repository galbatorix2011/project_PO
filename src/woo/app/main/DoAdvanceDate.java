package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.app.exception.InvalidDateException;
import woo.core.exception.InvalidDaysException;
//FIXME import other classes

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<StoreManager> {
  
  //FIXME add input fields
  private Input<Integer> _days;

  public DoAdvanceDate(StoreManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _days = _form.addIntegerInput(Message.requestDaysToAdvance());
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException, InvalidDateException {
    int days;
    _form.parse();
    days = _days.value();
    try{
      _receiver.advanceDate(days);
    } catch (InvalidDaysException e){
      throw new InvalidDateException(e.getValue());
    }
  }
}

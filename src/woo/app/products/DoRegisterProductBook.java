package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownServiceTypeException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateProductException;
import woo.core.exception.NonExistentSupplierException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.DuplicateProductKeyException;
import woo.core.exception.WrongServiceTypeException;
//FIXME import other classes

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<StoreManager> {

  private Input<String> _productKey;
  private Input<Integer> _price;
  private Input<Integer> _criticalValue;
  private Input<String> _supplierKey;
  private Input<String> _title;
  private Input<String> _author;
  private Input<String> _isbn;

  public DoRegisterProductBook(StoreManager receiver) {
    super(Label.REGISTER_BOOK, receiver);
    _productKey = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _criticalValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
    _title = _form.addStringInput(Message.requestBookTitle());
    _author = _form.addStringInput(Message.requestBookAuthor());
    _isbn = _form.addStringInput(Message.requestISBN());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerBook(_productKey.value(), _price.value(), _criticalValue.value(), _supplierKey.value(),
              _title.value(), _author.value(), _isbn.value());
    }catch (DuplicateProductException e1){
      throw new DuplicateProductKeyException(e1.getMessage());
    } catch (NonExistentSupplierException e2){
      throw new UnknownSupplierKeyException(e2.getMessage());
    }
  }
}

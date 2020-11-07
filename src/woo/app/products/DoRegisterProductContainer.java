package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownServiceLevelException;
import woo.app.exception.UnknownServiceTypeException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateProductException;
import woo.core.exception.NonExistentSupplierException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.DuplicateProductKeyException;
import woo.core.exception.WrongServiceTypeException;
import woo.core.exception.WrongServiceLevelException;
//FIXME import other classes

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<StoreManager> {

  private Input<String> _productKey;
  private Input<Integer> _price;
  private Input<Integer> _criticalValue;
  private Input<String> _supplierKey;
  private Input<String> _typeServiceString;
  private Input<String> _qualityServiceString;

  public DoRegisterProductContainer(StoreManager receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _productKey = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _criticalValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
    _typeServiceString = _form.addStringInput(Message.requestServiceType());
    _qualityServiceString = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerContainer(_productKey.value(), _price.value(), _criticalValue.value(), _supplierKey.value(),
              _typeServiceString.value(), _qualityServiceString.value());
    }catch (DuplicateProductException e1){
      throw new DuplicateProductKeyException(e1.getMessage());
    } catch (NonExistentSupplierException e2){
      throw new UnknownSupplierKeyException(e2.getMessage());
    } catch (WrongServiceTypeException e3){
      throw new UnknownServiceTypeException(_typeServiceString.value());
    }catch (WrongServiceLevelException e4){
      throw new UnknownServiceLevelException(_qualityServiceString.value());
    }
  }
}

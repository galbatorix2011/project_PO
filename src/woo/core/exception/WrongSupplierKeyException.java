package woo.core.exception;

public class WrongSupplierKeyException extends Exception{
    /** Unknown key. */
    private String _key;

    public WrongSupplierKeyException(String key) {
        _key = key;
    }

    public String getMessage() {
        return _key;
    }
}

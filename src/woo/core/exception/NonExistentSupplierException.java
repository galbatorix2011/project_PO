package woo.core.exception;

public class NonExistentSupplierException extends Exception{
    /** Unknown key. */
    private String _key;

    public NonExistentSupplierException(String key) {
        _key = key;
    }

    public String getMessage() {
        return _key;
    }

}

package woo.core.exception;

import woo.app.exception.Message;

public class DuplicateSupplierException extends Exception{
    /** Supplier key. */
    private String _key;

    public DuplicateSupplierException(String key) {
        _key = key;
    }

    public String getMessage() {
        return _key;
    }
}

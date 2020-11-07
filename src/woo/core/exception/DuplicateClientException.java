package woo.core.exception;

import woo.app.exception.Message;

public class DuplicateClientException extends Exception{

    /** Client key. */
    private String _key;

    public DuplicateClientException(String key) {
        _key = key;
    }

    public String getMessage() {
        return _key;
    }
}

package woo.core.exception;

public class WrongClientKeyException extends Exception{

    /** Unknown key. */
    private String _key;

    public WrongClientKeyException(String key) {
        _key = key;
    }

    public String getMessage() {
        return _key;
    }
}
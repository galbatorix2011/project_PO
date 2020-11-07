package woo.core.exception;

public class DuplicateProductException extends Exception{

    private String _key;

    public DuplicateProductException(String key) {
        _key = key;
    }

    public String getMessage() {
        return _key;
    }
}

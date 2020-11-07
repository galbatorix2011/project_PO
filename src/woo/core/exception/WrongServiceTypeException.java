package woo.core.exception;

public class WrongServiceTypeException extends Exception{
    private String _service;

    public WrongServiceTypeException(String service) {
        _service = service;
    }

    public String getMessage() {
        return _service;
    }
}

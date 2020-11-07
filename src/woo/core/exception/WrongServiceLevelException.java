package woo.core.exception;

public class WrongServiceLevelException extends Exception{
    private String _service;

    public WrongServiceLevelException(String service) {
        _service = service;
    }

    public String getMessage() {
        return _service;
    }
}

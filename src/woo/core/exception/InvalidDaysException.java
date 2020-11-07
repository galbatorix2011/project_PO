package woo.core.exception;

import woo.app.exception.Message;

public class InvalidDaysException extends Exception{
    /** Bad date. */
    private int _date;

    public InvalidDaysException(int date) {
        _date = date;
    }

    public int getValue(){
        return _date;
    }
}

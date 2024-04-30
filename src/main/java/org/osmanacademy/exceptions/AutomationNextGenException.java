package org.osmanacademy.exceptions;

public class AutomationNextGenException extends RuntimeException {

    public AutomationNextGenException() {
        super();
    }

    public AutomationNextGenException(String message) {
        super(message);
    }

    public AutomationNextGenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomationNextGenException(Throwable cause) {
        super(cause);
    }

    protected AutomationNextGenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

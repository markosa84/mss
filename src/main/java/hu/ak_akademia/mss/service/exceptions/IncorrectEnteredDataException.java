package hu.ak_akademia.mss.service.exceptions;

public class IncorrectEnteredDataException extends Exception {

    public IncorrectEnteredDataException() {
        super();
    }

    public IncorrectEnteredDataException(String message) {
        super(message);
    }

    public IncorrectEnteredDataException(String message, Throwable cause) {
        super(message, cause);
    }

}

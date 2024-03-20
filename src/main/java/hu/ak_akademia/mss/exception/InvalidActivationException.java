package hu.ak_akademia.mss.exception;

public class ExpiredActivationException extends RuntimeException {

    public ExpiredActivationException(String message) {
        super(message);
    }
}

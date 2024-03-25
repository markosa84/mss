package hu.ak_akademia.mss.service.exceptions;

public class AppointmentExistException extends RuntimeException {

    public AppointmentExistException(String message) {
        super(message);
    }
}

package hu.ak_akademia.mss.service.exceptions;

public class AppointmentStatusNotFoundException extends RuntimeException {

    public AppointmentStatusNotFoundException(String message) {
        super(message);
    }
}

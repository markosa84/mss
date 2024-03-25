package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.service.exceptions.AppointmentExistException;
import hu.ak_akademia.mss.service.exceptions.AppointmentStatusNotFoundException;
import hu.ak_akademia.mss.service.exceptions.EmailMessagingException;
import hu.ak_akademia.mss.service.exceptions.InvalidActivationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        String bodyOfResponse = "IllegalArgumentException occurred:itt aztán végkép baj van ";
        return ResponseEntity.badRequest().body(bodyOfResponse + e.getMessage());
    }

    @ExceptionHandler(value = IllegalStateException.class)
    protected ResponseEntity<Object> handleIllegalStateException(IllegalStateException e) {
        String bodyOfResponse = "IllegalStateException occurred:  nagy a baj";
        return ResponseEntity.badRequest().body(bodyOfResponse + e.getMessage());
    }

    @ExceptionHandler(value = EmailMessagingException.class)
    protected ResponseEntity<Object> handleMessagingException(EmailMessagingException e) {
        String bodyOfResponse = "MessagingException occurred while: The e-mail required for sending could not be created!: ";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        String bodyOfResponse = "NoSuchElementException occurred: Element not  ";
        return ResponseEntity.badRequest().body(bodyOfResponse + e.getMessage());
    }

    @ExceptionHandler(value = {
            InvalidActivationException.class,
            AppointmentStatusNotFoundException.class,
            UsernameNotFoundException.class,
            EntityNotFoundException.class,})
    protected ResponseEntity<ErrorResponse> handleInvalidActivationException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(value = AppointmentExistException.class)
    protected ResponseEntity<ErrorResponse> handleAppointmentExistException(AppointmentExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, e.getMessage()));
    }

}

package hu.ak_akademia.mss.controller;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(value = MessagingException.class)
    protected ResponseEntity<Object> handleMessagingException(MessagingException e) {
        String bodyOfResponse = "MessagingException occurred: ";
        return ResponseEntity.badRequest().body(bodyOfResponse + e.getMessage());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        String bodyOfResponse = "NoSuchElementException occurred: Element not  ";
        return ResponseEntity.badRequest().body(bodyOfResponse + e.getMessage());
    }

}

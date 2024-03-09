package hu.ak_akademia.mss.controller;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, MessagingException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException e) {
        String bodyOfResponse = "This should be application specific: ";
        return ResponseEntity.badRequest().body(bodyOfResponse + e.getMessage());
    }
}

package hu.ak_akademia.mss.controller;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {
}

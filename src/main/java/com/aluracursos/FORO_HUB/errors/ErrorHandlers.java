package com.aluracursos.FORO_HUB.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> loginExceptionHandler(LoginException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error","Unauthorized","message",exception.getMessage()));
    }

    @ExceptionHandler(UpdateTopicException.class)
    public ResponseEntity<?> updateTopicHandler(UpdateTopicException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error","Unauthorized","message",exception.getMessage()));
    }
}

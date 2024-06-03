package ru.gb.springdemo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> catchNoSuchElementException(NoSuchElementException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchIllegalStateException(IllegalStateException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), exception.getMessage()), HttpStatus.CONFLICT);
    }
}

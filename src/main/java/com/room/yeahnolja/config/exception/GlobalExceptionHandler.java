package com.room.yeahnolja.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * Controller 내에서 발생하는 Exception 대해서 Catch 하여 응답값(Response)을 보내주는 기능을 수행함.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("ResourceNotFoundException occurred", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("요청하신 데이터에 대한 결과가 존재하지 않습니다.");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.error("EmptyResultDataAccessException occurred", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("데이터가 존재하지 않습니다.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("객실이 존재하지 않거나, 해당 날짜에는 예약이 불가능합니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        log.error("Exception occurred", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("관리자에게 문의 바랍니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException occurred", e);

        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(","));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }
}
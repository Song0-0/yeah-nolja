package com.room.yeahnolja.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller 내에서 발생하는 Exception 대해서 Catch 하여 응답값(Response)을 보내주는 기능을 수행함.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * [Exception] 클라이언트로부터 받은 고유값으로 해당 데이터를 찾을 수 없는 경우
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("ResourceNotFoundException occurred", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("요청하신 데이터에 대한 결과가 존재하지 않습니다.");
    }

    /**
     * [Exception] EmptyResultDataAccessException 처리
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.error("EmptyResultDataAccessException occurred", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("데이터가 존재하지 않습니다.");
    }

    /**
     * [Exception] 그 외 모든 Exception 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception occurred", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("알 수 없는 오류가 발생했습니다.");
    }
}
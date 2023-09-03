package com.room.yeahnolja.config.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.room.yeahnolja.common.code.ErrorCode;
import com.room.yeahnolja.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * Controller 내에서 발생하는 Exception 대해서 Catch 하여 응답값(Response)을 보내주는 기능을 수행함.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;

    /**
     * [Exception] 클라이언트로부터 받은 고유값으로 해당 데이터를 찾을 수 없는 경우
     * @param e
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    /**
//     * TODO:
//     * 참고용
//     */
//    /**
//     * [Exception] 클라이언트에서 Body로 '객체' 데이터가 넘어오지 않았을 경우
//     *
//     * @param ex HttpMessageNotReadableException
//     * @return ResponseEntity<ErrorResponse>
//     */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
//            HttpMessageNotReadableException ex) {
//        log.error("HttpMessageNotReadableException", ex);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.REQUEST_BODY_MISSING_ERROR, ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * [Exception] 클라이언트에서 request로 '파라미터로' 데이터가 넘어오지 않았을 경우
//     *
//     * @param ex MissingServletRequestParameterException
//     * @return ResponseEntity<ErrorResponse>
//     */
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderExceptionException(
//            MissingServletRequestParameterException ex) {
//        log.error("handleMissingServletRequestParameterException", ex);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAMETER_ERROR, ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//
//    /**
//     * [Exception] 잘못된 서버 요청일 경우 발생한 경우
//     *
//     * @param e HttpClientErrorException
//     * @return ResponseEntity<ErrorResponse>
//     */
//    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
//    protected ResponseEntity<ErrorResponse> handleBadRequestException(HttpClientErrorException e) {
//        log.error("HttpClientErrorException.BadRequest", e);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.BAD_REQUEST_ERROR, e.getMessage());
//        return new ResponseEntity<>(response, HTTP_STATUS_OK);
//    }
//
//
//    /**
//     * [Exception] 잘못된 주소로 요청한 경우
//     *
//     * @param e NoHandlerFoundException
//     * @return ResponseEntity<ErrorResponse>
//     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    protected ResponseEntity<ErrorResponse> handleNoHandlerFoundExceptionException(NoHandlerFoundException e) {
//        log.error("handleNoHandlerFoundExceptionException", e);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_ERROR, e.getMessage());
//        return new ResponseEntity<>(response, HTTP_STATUS_OK);
//    }
//
//
//    /**
//     * [Exception] NULL 값이 발생한 경우
//     *
//     * @param e NullPointerException
//     * @return ResponseEntity<ErrorResponse>
//     */
//    @ExceptionHandler(NullPointerException.class)
//    protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
//        log.error("handleNullPointerException", e);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.NULL_POINT_ERROR, e.getMessage());
//        return new ResponseEntity<>(response, HTTP_STATUS_OK);
//    }
//
//    // ==================================================================================================================
//
//    /**
//     * [Exception] 모든 Exception 경우 발생
//     *
//     * @param ex Exception
//     * @return ResponseEntity<ErrorResponse>
//     */
//    @ExceptionHandler(Exception.class)
//    protected final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
//        log.error("Exception", ex);
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
//        return new ResponseEntity<>(response, HTTP_STATUS_OK);
//    }
}
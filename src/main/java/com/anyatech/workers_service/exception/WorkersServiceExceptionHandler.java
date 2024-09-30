package com.anyatech.workers_service.exception;

import lombok.extern.slf4j.Slf4j;
import model.Worker;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class WorkersServiceExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Worker> handleRuntimeException(RuntimeException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new Worker(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleApiValidationException(Exception exception) {

        log.error(exception.getMessage());
        ErrorResponseDto errorResponseDto = null;

        if (exception instanceof MethodArgumentTypeMismatchException){
            errorResponseDto = new ErrorResponseDto(ReasonCode.METHOD_ARG_TYPE_MISMATCH.getCode());
            return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
        }

        errorResponseDto = new ErrorResponseDto(ReasonCode.INTERNAL_SERVER_ERROR.getCode());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Worker> handleException(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new Worker(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

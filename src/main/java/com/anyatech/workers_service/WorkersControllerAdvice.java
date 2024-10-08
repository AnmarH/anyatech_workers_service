package com.anyatech.workers_service;

import com.anyatech.workers_service.exception.ReasonCode;
import com.anyatech.workers_service.exception.WorkersApiException;
import com.anyatech.workers_service.exception.WorkersServiceErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class WorkersControllerAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<WorkersServiceErrorResponseDto> handleApiValidationException(Exception exception) throws WorkersApiException {

        log.error(exception.getMessage());
        WorkersServiceErrorResponseDto errorResponseDto = null;

        if (exception instanceof MethodArgumentTypeMismatchException){
            errorResponseDto = new WorkersServiceErrorResponseDto(ReasonCode.METHOD_ARG_TYPE_MISMATCH.getCode());
            throw new WorkersApiException();
            //return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }

        errorResponseDto = new WorkersServiceErrorResponseDto(ReasonCode.INTERNAL_SERVER_ERROR.getCode());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);

    }




}

package com.anyatech.workers_service.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ErrorResponseDto {

    private int code;

    public ErrorResponseDto(int code) {
        this.code = code;
    }


}

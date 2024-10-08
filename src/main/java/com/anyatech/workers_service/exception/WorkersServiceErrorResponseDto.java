package com.anyatech.workers_service.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class WorkersServiceErrorResponseDto {

    private int code;

    public WorkersServiceErrorResponseDto(int code) {
        this.code = code;
    }


}

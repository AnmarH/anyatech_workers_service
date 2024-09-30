package com.anyatech.workers_service.exception;

import lombok.Getter;

@Getter
public enum ReasonCode {

    METHOD_ARG_TYPE_MISMATCH(1001,
            "The variable type provided does not match the method argument type, " +
                    "e.g. you provided a character when method argument was expecting a numeric value"),
    INTERNAL_SERVER_ERROR(1002, "The root cause of the api validation error could not be determine, " +
            "so a default internal server error was used.");

    private final int code;
    private final String description;

    ReasonCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}

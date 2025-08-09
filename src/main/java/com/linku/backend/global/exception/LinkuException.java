package com.linku.backend.global.exception;

import com.linku.backend.global.response.ResponseCode;
import lombok.Getter;

@Getter
public class LinkuException extends RuntimeException {
    private final int code;
    private final String message;

    private LinkuException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    static public LinkuException of(ResponseCode responseCode){
        return new LinkuException(responseCode.getCode(), responseCode.getMessage());
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.linku.backend.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseResponse<T> {
    private final boolean isSuccess;
    private final int code;
    private final String message;
    private final T result;

    public static <T> BaseResponse<T> of(boolean isSuccess, int code, String message, T result) {
        return new BaseResponse<>(
                isSuccess,
                code,
                message,
                result
        );
    }

    public static <T> BaseResponse<T> of(ResponseCode responseCode, T result) {
        return new BaseResponse<>(
                responseCode.isSuccess(),
                responseCode.getCode(),
                responseCode.getMessage(),
                result
        );
    }
}

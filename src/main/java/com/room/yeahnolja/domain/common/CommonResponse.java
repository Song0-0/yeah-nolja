package com.room.yeahnolja.domain.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;

    @Builder
    public CommonResponse(int code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> onSuccess(int code, T data) {
        return CommonResponse.<T>builder()
                .code(code)
                .success(true)
                .data(data)
                .build();
    }

}

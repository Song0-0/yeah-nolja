package com.room.yeahnolja.common.code;

import lombok.Getter;

@Getter
public enum SuccessCode {

    SELECT_SUCCESS(200, "200", "SELECT_SUCCESS"),
    DELETE_SUCCESS(200, "200", "DELETE SUCCESS"),
    INSERT_SUCCESS(201, "201", "INSERT SUCCESS"),
    UPDATE_SUCCESS(204, "204", "UPDATE SUCCESS");
    private final int status;
    private final String code;
    private final String message;

    SuccessCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

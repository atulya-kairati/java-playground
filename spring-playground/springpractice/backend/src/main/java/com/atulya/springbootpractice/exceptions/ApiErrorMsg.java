package com.atulya.springbootpractice.exceptions;

import java.time.LocalDateTime;

public record ApiErrorMsg(
        String path,
        String msg,
        int statusCode,
        LocalDateTime dateTime
) {
}

package com.vai.vmcapi.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Data
public class BusinessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    private int code;
    private String message;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BusinessException() {
    }

    public BusinessException(AppErrorCode errorCode) {
        super(errorCode.getMsg());
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message) {
        this.message = message;
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

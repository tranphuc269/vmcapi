package com.vai.vmcapi.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BusinessException{
    public NotFoundUserException(){
        super(404, "Not found user or user inactive", HttpStatus.NOT_FOUND);
    }
}

package com.vai.vmcapi.domain.exception;

public interface AppErrorCode {


    String LEVEL_SYSTEM = "system";


    String LEVEL_USER = "user";


    String LEVEL_UNEXPECTED = "unexpected";

    int getCode();


    String getMsg();

    String getLevel();
}
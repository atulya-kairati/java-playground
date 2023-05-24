package com.atulya.exceptions;

public class MyUncheckedException extends RuntimeException{
    // unchecked exceptions extends RuntimeException
    public MyUncheckedException(String msg){
        super(msg);
    }
}

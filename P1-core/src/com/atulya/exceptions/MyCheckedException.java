package com.atulya.exceptions;

public class MyCheckedException extends Exception{
    // checked exceptions extends Exception class
    public MyCheckedException(String msg){
        super(msg);
    }
}

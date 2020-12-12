package com.Nona9961.exceptions;

public class SimpleMVCException extends Exception {
    public SimpleMVCException(SimpleMVCExceptionEnum exceptionEnum) {
        super(exceptionEnum.toString());
    }
}

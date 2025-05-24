package com.GeekUp.Shop.exception;


import org.springframework.http.HttpStatus;

public class DuplicateException extends Exception{
    private String duplicateField;
    private HttpStatus httpStatus = HttpStatus.CONFLICT;
    private String errorCode ;

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, String duplicateField) {
        super(message);
        this.duplicateField = duplicateField;
//        this.errorCode = ErrorCode.DUPLICATE + "_" + duplicateField;
    }
}

package com.example.filereader.exception;

public class ValidationException extends RuntimeException {
    private String errorCode;
    private String errorDescription;

    public ValidationException(String errorCode){
        this.errorCode = errorCode;
        this.errorDescription = ExceptionCodeHolder.getMessage(errorCode);
    }
    public ValidationException(String errorCode, String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}

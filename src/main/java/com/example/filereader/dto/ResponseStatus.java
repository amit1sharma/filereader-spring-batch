package com.example.filereader.dto;

import com.example.filereader.exception.ExceptionCodeHolder;

public class ResponseStatus {
    private String statusCode;
    private String statusDescription;
    private String errorCode;
    private String errorMessage;

    public ResponseStatus(String statusCode) {
        this.statusCode = statusCode;
        this.statusDescription = ExceptionCodeHolder.getMessage(statusCode);
    }

    public ResponseStatus() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "statusCode='" + statusCode + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

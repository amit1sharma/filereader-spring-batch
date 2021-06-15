package com.example.filereader.exception;

import com.example.filereader.dto.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.SizeLimitExceededException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseStatus> validationException(ValidationException ve){
        ResponseStatus rs = new ResponseStatus("101");
        rs.setErrorCode(ve.getErrorCode());
        rs.setErrorMessage(ve.getErrorDescription());
        System.out.println(rs.toString());
        return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
    }

    /*@org.springframework.web.bind.annotation.ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<ResponseStatus> validationException(SizeLimitExceededException ve){
        ResponseStatus rs = new ResponseStatus("101");
        rs.setErrorCode("800");
        rs.setErrorMessage(ve.getMessage());
        System.out.println(rs.toString());
        ve.printStackTrace();
        return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
    }*/
}
